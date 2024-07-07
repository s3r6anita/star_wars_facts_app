package com.f4.starwarsfactsapp.data.network

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getPersons(page: Int? = null): NetworkResult<PersonsFactsResponse>
    suspend fun getNewPersons(page: Int? = null): NetworkResult<PersonsFactsResponse>
    suspend fun getPersons(): Flow<PersonsFactsResponse>
    suspend fun savePersons(facts: PersonsFactsResponse)
    suspend fun getPersonFacts(personId: Int): NetworkResult<PersonFacts>
}