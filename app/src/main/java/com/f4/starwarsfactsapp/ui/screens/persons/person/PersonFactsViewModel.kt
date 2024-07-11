package com.f4.starwarsfactsapp.ui.screens.persons.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f4.starwarsfactsapp.data.PersonRepository
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.Person
import com.f4.starwarsfactsapp.ui.screens.persons.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonFactsViewModel @Inject constructor(
    private val personRepository: PersonRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _facts = MutableStateFlow<Person?>(null)
    val facts = _facts.asStateFlow()

    fun getPeopleFact(peopleId: Int) {
        _uiState.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = personRepository.getPersonFacts(peopleId)) {
                is NetworkResult.Success -> {
                    _facts.value = response.data
                    _uiState.value = UIState.Success(_facts.value)
                }
                is NetworkResult.Error -> { _uiState.value = UIState.Error(msg = response.errorMsg, _facts.value) }
                is NetworkResult.Exception -> { _uiState.value = UIState.Error(msg = response.e.message ?: "", _facts.value) }
            }
        }
    }
}
