package com.f4.starwarsfactsapp.data.network.service

import com.f4.starwarsfactsapp.data.model.GetFilmsResponse
import com.f4.starwarsfactsapp.data.model.GetPersonsResponse
import com.f4.starwarsfactsapp.data.model.GetPlanetsResponse
import com.f4.starwarsfactsapp.data.model.Person
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsService {
    @GET("people/")
    suspend fun getPersons(@Query("page") page: Int?): Response<GetPersonsResponse>

    @GET("people/{id}")
    suspend fun getPersonFact(@Path("id") id: Int): Response<Person>

    @GET("films/")
    suspend fun getFilms(): Response<GetFilmsResponse>

    @GET("planets/")
    suspend fun getPlanets(@Query("page") page: Int?): Response<GetPlanetsResponse>
}
