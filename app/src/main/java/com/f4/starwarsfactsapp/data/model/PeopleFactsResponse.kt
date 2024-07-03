package com.f4.starwarsfactsapp.data.model

data class PeopleFactsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PersonFacts>
)
