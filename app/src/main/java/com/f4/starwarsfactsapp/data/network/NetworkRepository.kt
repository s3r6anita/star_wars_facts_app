package com.f4.starwarsfactsapp.data.network

import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.model.PeopleFactsResponse

interface NetworkRepository {
    suspend fun getPersonFacts(personId: Int): NetworkResult<PersonFacts>
    suspend fun getPeopleFacts(page: Int? = null): NetworkResult<PeopleFactsResponse>
}