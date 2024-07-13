package com.f4.starwarsfactsapp.data

import com.f4.starwarsfactsapp.data.model.Person
import com.f4.starwarsfactsapp.data.model.PersonResponse
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse

interface PersonRepository {
    suspend fun getPersons(page: Int? = null): PersonsFactsResponse
    suspend fun getNewPersons(): PersonsFactsResponse?
    suspend fun getLocalPersonFacts(personId: Int): Person
    suspend fun refreshPersonFacts(personId: Int): PersonResponse
}