package com.f4.starwarsfactsapp.ui.screens.listFacts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.f4.starwarsfactsapp.ui.screens.ErrorScreen
import com.f4.starwarsfactsapp.ui.screens.LoadingScreen
import com.f4.starwarsfactsapp.ui.screens.UIState
import kotlinx.coroutines.launch

@Composable
fun ListFactsScreen(
    navigate: (String) -> Unit,
    viewModel: ListFactsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UIState.Loading -> {
            LoadingScreen()
        }

        is UIState.Success -> {
            SuccessScreen(
                navigate = navigate
            )
        }

        is UIState.Error -> {
            ErrorScreen(
                message = (uiState as UIState.Error).msg,
                retryAction = { scope.launch { viewModel.getPeopleFacts() } }
            )
        }
    }
}
