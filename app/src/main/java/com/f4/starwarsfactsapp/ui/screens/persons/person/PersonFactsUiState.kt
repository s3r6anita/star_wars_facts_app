package com.f4.starwarsfactsapp.ui.screens.persons.person

import com.f4.starwarsfactsapp.data.model.Person

data class PersonFactsUiState(
    val person: Person = Person(),
    val filmTitles: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMsg: String? = null
)