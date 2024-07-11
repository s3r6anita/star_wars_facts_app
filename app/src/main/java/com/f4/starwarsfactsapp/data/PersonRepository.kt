package com.f4.starwarsfactsapp.data

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.Person
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse

interface PersonRepository {
    suspend fun getPersons(page: Int? = null): PersonsFactsResponse
    suspend fun getNewPersons(): PersonsFactsResponse?
    suspend fun getPersonFacts(personId: Int): NetworkResult<Person>
}