package com.f4.starwarsfactsapp.data.network

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PeopleFact
import retrofit2.Response

interface NetworkRepository {
    suspend fun getPeopleFact(): NetworkResult<PeopleFact>
    suspend fun getPeopleFacts(page: Int?): NetworkResult<List<PeopleFact>>
}