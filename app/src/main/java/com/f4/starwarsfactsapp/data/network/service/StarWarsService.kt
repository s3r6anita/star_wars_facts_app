package com.f4.starwarsfactsapp.data.network.service

import com.f4.starwarsfactsapp.data.model.Person
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsService {
    @GET("people/")
    suspend fun getPersons(@Query("page") page: Int?): Response<PersonsFactsResponse>

    @GET("people/{id}")
    suspend fun getPersonFact(@Path("id") id: Int): Response<Person>
}
