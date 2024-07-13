package com.f4.starwarsfactsapp.data.model

import com.google.gson.annotations.SerializedName

data class Person(
    val name: String = "",
    val height: String = "",
    val mass: String = "",
    @SerializedName("hair_color") val hairColor: String = "",
    @SerializedName("skin_color") val skinColor: String = "",
    @SerializedName("eye_color") val eyeColor: String = "",
    @SerializedName("birth_year") val birthYear: String = "",
    val gender: String = "",
    val homeworld: String = "",
    val films: List<String> = emptyList(),
    val species: List<String> = emptyList(),
    val vehicles: List<String> = emptyList(),
    val starships: List<String> = emptyList(),
    val created: String = "",
    val edited: String = "",
    val url: String = ""
)

