package com.f4.starwarsfactsapp.ui.screens.persons.person

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.f4.starwarsfactsapp.ui.screens.ErrorScreen
import com.f4.starwarsfactsapp.ui.screens.LoadingScreen
import com.f4.starwarsfactsapp.ui.screens.persons.UIState
import kotlinx.coroutines.launch

@Composable
fun PersonScreen(
    personId: Int,
    navigateUp: () -> Unit,
    viewModel: PersonFactsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()
    val facts by viewModel.facts.collectAsState()

    LaunchedEffect(Unit) {
        scope.launch { viewModel.getPeopleFact(personId) }
    }

    when (uiState) {
        is UIState.Loading -> {
            LoadingScreen()
        }

        is UIState.Success<*> -> {
            SuccessScreen(facts!!, navigateUp)
        }

        is UIState.Error<*> -> {
            ErrorScreen(
                message = (uiState as UIState.Error<*>).msg,
                retryAction = { scope.launch { viewModel.getPeopleFact(personId) } }
            )
        }
    }
}