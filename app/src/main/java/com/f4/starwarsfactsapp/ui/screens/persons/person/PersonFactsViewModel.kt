package com.f4.starwarsfactsapp.ui.screens.persons.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f4.starwarsfactsapp.data.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonFactsViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonFactsUiState())
    val uiState = _uiState.asStateFlow()

    fun getPersonFacts(personId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val facts = personRepository.getLocalPersonFacts(personId)
            _uiState.update {
                it.copy(
                    person = facts,
                    isLoading = false
                )
            }
            return@launch
        }
    }

    fun refreshPersonFact(personId: Int){
        _uiState.update { it.copy(isRefreshing = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val response = personRepository.refreshPersonFacts(personId)
            _uiState.update {
                it.copy(
                    person = response.person,
                    isRefreshing = false,
                    errorMsg = response.error
                )
            }
            return@launch
        }
    }

    fun userMessageShown() {
        _uiState.update { it.copy(errorMsg = null) }
    }
}
