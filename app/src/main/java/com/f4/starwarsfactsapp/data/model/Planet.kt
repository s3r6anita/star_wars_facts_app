package com.f4.starwarsfactsapp.data.model

import com.google.gson.annotations.SerializedName

data class Planet(
    val climate: String = "",
    val created: String = "",
    val diameter: String = "",
    val edited: String = "",
    val films: List<String> = emptyList(),
    val gravity: String = "",
    val name: String = "",
    @SerializedName("orbital_period") val orbitalPeriod: String = "",
    val population: String = "",
    val residents: List<String> = emptyList(),
    @SerializedName("rotation_period") val rotationPeriod: String = "",
    @SerializedName("surface_water") val surfaceWater: String = "",
    val terrain: String = "",
    val url: String = ""
)