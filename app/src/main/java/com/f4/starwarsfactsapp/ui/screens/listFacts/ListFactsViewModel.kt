package com.f4.starwarsfactsapp.ui.screens.listFacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.model.PeopleFact
import com.f4.starwarsfactsapp.data.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFactsViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
): ViewModel() {
    private val _factsUiState = MutableStateFlow(emptyList<PeopleFact>())
    val factsUiState = _factsUiState.asStateFlow()


}