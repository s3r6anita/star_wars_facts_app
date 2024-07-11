package com.f4.starwarsfactsapp.data.model

data class PersonsFactsResponse(
    val count: Int = -1,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Person> = emptyList(),
    val error: String? = null
)
