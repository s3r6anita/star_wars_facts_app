package com.f4.starwarsfactsapp.data.model

data class GetPlanetsResponse(
    val count: Int = -1,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Planet> = emptyList(),
    val error: String? = null
)
