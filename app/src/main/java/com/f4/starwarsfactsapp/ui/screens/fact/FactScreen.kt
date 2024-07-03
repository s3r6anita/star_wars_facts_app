package com.f4.starwarsfactsapp.ui.screens.fact

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.ui.screens.ErrorScreen
import com.f4.starwarsfactsapp.ui.screens.LoadingScreen
import com.f4.starwarsfactsapp.ui.screens.UIState
import kotlinx.coroutines.launch

@Composable
fun FactScreen(
    factId: Int,
    viewModel: FactViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        scope.launch { viewModel.getPeopleFact(factId) }
    }

    when (uiState) {
        is UIState.Loading -> {
            LoadingScreen()
        }

        is UIState.Success -> {
            SuccessScreen((uiState as UIState.Success).data as PersonFacts)
        }

        is UIState.Error -> {
            ErrorScreen(
                message = (uiState as UIState.Error).msg,
                retryAction = { scope.launch { viewModel.getPeopleFact(factId) } }
            )
        }
    }
}