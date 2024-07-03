package com.f4.starwarsfactsapp.data.network

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PeopleFact
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService
): NetworkRepository, ApiHandler {
    override suspend fun getPeopleFact(): NetworkResult<PeopleFact> {
        TODO("Not yet implemented")
    }

    override suspend fun getPeopleFacts(page: Int?): NetworkResult<List<PeopleFact>> {
        return handleApi { starWarsService.getPeopleFacts(page) }
    }
}