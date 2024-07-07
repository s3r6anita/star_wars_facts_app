package com.f4.starwarsfactsapp.ui.screens.listPersons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.data.network.PersonRepository
import com.f4.starwarsfactsapp.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFactsViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _next = MutableStateFlow<String?>(null)
    val next = _next.asStateFlow()
    private val _persons = MutableStateFlow<List<PersonFacts>>(emptyList())
    val persons = _persons.asStateFlow()

    init {
        getPeopleFacts()
    }

    fun getPeopleFacts(page: Int? = null) {
        _uiState.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = personRepository.getPersons(page)) {
                is NetworkResult.Success -> {
                    _persons.value = response.data.results
                    _next.value = response.data.next
                    _uiState.value = UIState.Success

//                    personRepository.savePersons(response.data)
                }

                is NetworkResult.Error -> {
                    _uiState.value = UIState.Error(msg = response.errorMsg)
//                    _uiState.value = UIState.Success
//                    personRepository.getPersons().collect {
//                        _persons.value = it.results
//                    }
                }

                is NetworkResult.Exception -> {
                    _uiState.value = UIState.Error(msg = response.e.message ?: "")

//                    _uiState.value = UIState.Success
//                    personRepository.getPersons().collect {
//                        _persons.value = it.results
//                    }
                }
            }
        }
    }

    fun addPeopleFacts(page: Int? = null) {
//        _uiState.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = personRepository.getNewPersons(page)) {
                is NetworkResult.Success -> {
                    _persons.value += response.data.results
                    _next.value = response.data.next
                    _uiState.value = UIState.Success
                }

                is NetworkResult.Error -> {
                    _uiState.value = UIState.Error(msg = response.errorMsg)
                }

                is NetworkResult.Exception -> {
                    _uiState.value = UIState.Error(msg = response.e.message ?: "")
                }
            }
        }
    }
}
