package com.f4.starwarsfactsapp.ui.screens.persons.list

import com.f4.starwarsfactsapp.data.model.Person

data class PersonsUiState(
    val persons: List<Person> = emptyList(),
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)