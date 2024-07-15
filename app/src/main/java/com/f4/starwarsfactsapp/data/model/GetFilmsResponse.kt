package com.f4.starwarsfactsapp.data.model

data class GetFilmsResponse(
    val count: Int = -1,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Film> = emptyList(),
    val error: String? = null
)