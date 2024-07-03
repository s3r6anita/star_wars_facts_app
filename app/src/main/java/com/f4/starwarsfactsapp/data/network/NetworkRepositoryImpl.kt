package com.f4.starwarsfactsapp.data.network

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.model.PeopleFactsResponse
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService
): NetworkRepository, ApiHandler {
    override suspend fun getPersonFacts(personId: Int): NetworkResult<PersonFacts> {
        return handleApi { starWarsService.getPeopleFact(personId) }
    }

    override suspend fun getPeopleFacts(page: Int?): NetworkResult<PeopleFactsResponse> {
        return handleApi { starWarsService.getPeopleFacts(page) }
    }
}