package com.f4.starwarsfactsapp.ui.screens.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.network.NetworkRepository
import com.f4.starwarsfactsapp.ui.screens.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getPeopleFact(peopleId: Int) {
        _uiState.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = networkRepository.getPersonFacts(peopleId)) {
                is NetworkResult.Success -> {_uiState.value = UIState.Success(response.data) }
                is NetworkResult.Error -> { _uiState.value = UIState.Error(msg = response.errorMsg) }
                is NetworkResult.Exception -> { _uiState.value = UIState.Error(msg = response.e.message ?: "") }
            }
        }
    }


}
