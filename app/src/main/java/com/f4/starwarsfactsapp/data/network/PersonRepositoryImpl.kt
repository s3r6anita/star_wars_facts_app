package com.f4.starwarsfactsapp.data.network

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService
) : PersonRepository, ApiHandler {

    override suspend fun getPersons(page: Int?): NetworkResult<PersonsFactsResponse> {
        return handleApi { starWarsService.getPersons(page) }
    }

    override suspend fun getPersonFacts(personId: Int): NetworkResult<PersonFacts> {
        return handleApi { starWarsService.getPersonFact(personId) }
    }
}