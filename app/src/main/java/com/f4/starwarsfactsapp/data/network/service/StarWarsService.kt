package com.f4.starwarsfactsapp.data.network.service

import com.f4.starwarsfactsapp.data.model.PeopleFact
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsService {
    @GET("people")
    suspend fun getPeopleFacts(@Query("page") page: Int?): Response<List<PeopleFact>>

    @GET("people/{id}")
    suspend fun getPeopleFact(@Path("id") id: Int): Response<PeopleFact>
}