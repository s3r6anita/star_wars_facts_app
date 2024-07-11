package com.f4.starwarsfactsapp.ui.screens.persons.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.f4.starwarsfactsapp.R

@Composable
fun ListFactsScreen(
    snackbarHostState: SnackbarHostState,
    navigate: (String) -> Unit,
    viewModel: ListPersonsViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (scrollState.value == scrollState.maxValue) {
        LaunchedEffect(Unit) {
            viewModel.addPeopleFacts()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {

            if (uiState.isLoading) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
//                LoadingScreen()
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                        .verticalScroll(scrollState)
                        .fillMaxSize(),
                ) {
                    Text(
                        text = stringResource(R.string.persons_page_title),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(20.dp)
                    )
                    uiState.persons.forEach { person ->
                        FactItem(
                            person = person,
                            navigate = navigate
                        )
                    }
                }
            }
        }
    }


    uiState.errorMsg?.let { userMessage ->
        LaunchedEffect(userMessage) {
            snackbarHostState.showSnackbar(userMessage)
            viewModel.userMessageShown()
        }
    }
}
