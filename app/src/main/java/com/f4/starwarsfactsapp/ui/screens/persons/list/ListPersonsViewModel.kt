package com.f4.starwarsfactsapp.ui.screens.persons.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f4.starwarsfactsapp.data.OtherRepository
import com.f4.starwarsfactsapp.data.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPersonsViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val otherRepository: OtherRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getOtherInfo()
        getPersonsFacts()
    }

    private fun getOtherInfo(){

    }

    private fun getPersonsFacts(page: Int? = null) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val response = personRepository.getPersons(page)
            _uiState.update {
                it.copy(
                    persons = response.results,
                    isLoading = false,
                    errorMsg = response.error
                )
            }
            return@launch
        }
    }

    fun addPersonsFacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = personRepository.getNewPersons()
            response?.let {
                _uiState.update {
                    it.copy(
                        persons = it.persons + response.results,
                        errorMsg = response.error
                    )
                }
            }
            return@launch
        }
    }

    fun refreshPersonsFacts() {
        _uiState.update { it.copy(isRefreshing = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val response = personRepository.getPersons()
            _uiState.update {
                it.copy(
                    persons = response.results,
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
