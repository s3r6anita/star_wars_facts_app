package com.f4.starwarsfactsapp.ui.screens.listPersons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import com.f4.starwarsfactsapp.ui.UIState
import com.f4.starwarsfactsapp.ui.screens.LoadingScreen

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

        is UIState.Success<*> -> {
            SuccessScreen(
                navigate = navigate,
                persons = ((uiState as UIState.Success<*>).data as PersonsFactsResponse).results
            )
        }

        is UIState.Error<*> -> {
            SuccessScreen(
                navigate = navigate,
                persons = ((uiState as UIState.Error<*>).data as PersonsFactsResponse).results
            )
//            ErrorScreen(
//                message = (uiState as UIState.Error<*>).msg,
//                retryAction = { scope.launch { viewModel.getPeopleFacts() } }
//            )
        }
    }
}
