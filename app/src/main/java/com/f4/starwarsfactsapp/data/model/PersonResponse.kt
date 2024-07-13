package com.f4.starwarsfactsapp.data.model

data class PersonResponse(
    val person: Person,
    val error: String? = null
)
