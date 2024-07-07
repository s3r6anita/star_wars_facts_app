package com.f4.starwarsfactsapp.data.network

import androidx.datastore.core.DataStore
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val dataStore: DataStore<PersonsFactsResponse>
) : PersonRepository, ApiHandler {

    override suspend fun getPersons(page: Int?): NetworkResult<PersonsFactsResponse> {
        return handleApi { starWarsService.getPersons(page) }
    }

    override suspend fun savePersons(facts: PersonsFactsResponse) {
        dataStore.updateData { facts }
    }

    override suspend fun getPersons(): Flow<PersonsFactsResponse> = dataStore.data

    override suspend fun getNewPersons(page: Int?): NetworkResult<PersonsFactsResponse> {
        return handleApi { starWarsService.getPersons(page) }
    }

    override suspend fun getPersonFacts(personId: Int): NetworkResult<PersonFacts> {
        return handleApi { starWarsService.getPersonFact(personId) }
    }


}