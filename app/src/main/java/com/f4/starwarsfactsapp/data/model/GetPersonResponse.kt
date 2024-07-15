package com.f4.starwarsfactsapp.data.model

data class GetPersonResponse(
    val person: Person,
    val error: String? = null
)