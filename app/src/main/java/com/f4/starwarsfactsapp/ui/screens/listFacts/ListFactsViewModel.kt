package com.f4.starwarsfactsapp.ui.screens.listFacts

import android.util.Log
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
class ListFactsViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getPeopleFacts()
    }

    fun getPeopleFacts(page: Int? = null) {
        _uiState.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = networkRepository.getPeopleFacts(page)) {
                is NetworkResult.Success -> {_uiState.value = UIState.Success(response.data.results) }
                is NetworkResult.Error -> { _uiState.value = UIState.Error(msg = response.errorMsg) }
                is NetworkResult.Exception -> { _uiState.value = UIState.Error(msg = response.e.message ?: "") }
            }
        }
    }
}