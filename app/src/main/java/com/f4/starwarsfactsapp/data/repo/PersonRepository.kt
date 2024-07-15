package com.f4.starwarsfactsapp.data.repo

import com.f4.starwarsfactsapp.data.model.GetPersonResponse
import com.f4.starwarsfactsapp.data.model.GetPersonsResponse
import com.f4.starwarsfactsapp.data.model.Person

interface PersonRepository {
    suspend fun getPersons(page: Int? = null): GetPersonsResponse
    suspend fun getNewPersons(): GetPersonsResponse?
    suspend fun getLocalPersonFacts(personId: Int): Person
    suspend fun refreshPersonFacts(personId: Int): GetPersonResponse
}