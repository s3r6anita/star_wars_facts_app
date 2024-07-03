package com.f4.starwarsfactsapp.ui.screens.listFacts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun ListFactsScreen(
    viewModel: ListFactsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val facts by viewModel.factsUiState.collectAsState()

}