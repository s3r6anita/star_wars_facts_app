package com.f4.starwarsfactsapp.data.repo

import androidx.datastore.core.DataStore
import com.f4.starwarsfactsapp.data.model.GetPersonResponse
import com.f4.starwarsfactsapp.data.model.GetPersonsResponse
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.Person
import com.f4.starwarsfactsapp.data.network.ApiHandler
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import com.f4.starwarsfactsapp.util.getIdForUrl
import com.f4.starwarsfactsapp.util.getPageFromUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val dataStore: DataStore<GetPersonsResponse>
) : PersonRepository, ApiHandler {

    private var nextPageId: Int? = null

    private suspend fun savePersons(facts: GetPersonsResponse) {
        dataStore.updateData { facts }
    }

    private fun getLocalPersons(): Flow<GetPersonsResponse> = dataStore.data

    private suspend fun updatePerson(id: Int, person: Person) {
        val localData = getLocalPersons().first()
        val localPersons = localData.results.toMutableList()
        localPersons[id] = person
        val newData = localData.copy(results = localPersons.toList())
        dataStore.updateData { newData }
    }


    override suspend fun getPersons(page: Int?): GetPersonsResponse {
        when (val response = handleApi { starWarsService.getPersons(page) }) {
            is NetworkResult.Success -> {
                savePersons(response.data)
                nextPageId = getPageFromUrl(response.data.next)
                return response.data
            }

            is NetworkResult.Error -> {
                nextPageId = getPageFromUrl(dataStore.data.first().next)
                return getLocalPersons().first().copy(error = response.errorMsg)
            }

            is NetworkResult.Exception -> {
                nextPageId = getPageFromUrl(dataStore.data.first().next)
                return getLocalPersons().first().copy(error = response.e.message)
            }
        }
    }


    override suspend fun getNewPersons(): GetPersonsResponse? {
        if (nextPageId != -1) {
            when (val response = handleApi { starWarsService.getPersons(nextPageId) }) {
                is NetworkResult.Success -> {
                    val oldPersons = getLocalPersons().first().results
                    val newPersons = response.data.results
                    savePersons(response.data.copy(results = oldPersons + newPersons))
                    nextPageId = getPageFromUrl(response.data.next)
                    return response.data
                }

                is NetworkResult.Error -> {
                    return GetPersonsResponse().copy(error = response.errorMsg)
                }

                is NetworkResult.Exception -> {
                    return GetPersonsResponse().copy(error = response.e.message)
                }
            }
        } else
            return null
    }

    override suspend fun getLocalPersonFacts(personId: Int): Person {
        val personsFromMemory = getLocalPersons().first().results
        return personsFromMemory[personId]
    }

    override suspend fun refreshPersonFacts(personId: Int): GetPersonResponse {
        when (val response = handleApi { starWarsService.getPersonFact(getIdForUrl(personId)) }) {
            is NetworkResult.Success -> {
                updatePerson(personId, response.data)
                return GetPersonResponse(response.data)
            }

            is NetworkResult.Error -> {
                return GetPersonResponse(
                    person = getLocalPersonFacts(personId),
                    error = response.errorMsg
                )
            }

            is NetworkResult.Exception -> {
                return GetPersonResponse(
                    person = getLocalPersonFacts(personId),
                    error = response.e.message
                )
            }
        }

    }
}