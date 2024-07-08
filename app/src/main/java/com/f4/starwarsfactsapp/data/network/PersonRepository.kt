package com.f4.starwarsfactsapp.data.network

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import com.f4.starwarsfactsapp.ui.UIState

interface PersonRepository {
    suspend fun getPersons(page: Int? = null): UIState
    suspend fun getNewPersons(page: Int? = null): NetworkResult<PersonsFactsResponse>
    suspend fun getPersonFacts(personId: Int): NetworkResult<PersonFacts>
}