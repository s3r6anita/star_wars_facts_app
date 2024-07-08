package com.f4.starwarsfactsapp.data.network

import androidx.datastore.core.DataStore
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import com.f4.starwarsfactsapp.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val dataStore: DataStore<PersonsFactsResponse>
) : PersonRepository, ApiHandler {

    private suspend fun savePersons(facts: PersonsFactsResponse) {
        dataStore.updateData { facts }
    }

    private fun getLocalPersons(): Flow<PersonsFactsResponse> = dataStore.data

    override suspend fun getPersons(page: Int?): UIState {
        when (val response = handleApi { starWarsService.getPersons(page) }) {
            is NetworkResult.Success -> {
                savePersons(response.data)
                return UIState.Success(response.data)
            }

            is NetworkResult.Error -> {
                val res = getLocalPersons().first()
                return UIState.Error(msg = response.errorMsg, data = res)
            }

            is NetworkResult.Exception -> {
                val res = getLocalPersons().first()
                return UIState.Error(msg = response.e.message ?: "", data = res)
            }
        }
    }


    override suspend fun getNewPersons(page: Int?): NetworkResult<PersonsFactsResponse> {
        return handleApi { starWarsService.getPersons(page) }
    }

    override suspend fun getPersonFacts(personId: Int): NetworkResult<PersonFacts> {
        return handleApi { starWarsService.getPersonFact(personId) }
    }


}