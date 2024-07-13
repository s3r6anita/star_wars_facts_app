package com.f4.starwarsfactsapp.ui.screens.persons.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.f4.starwarsfactsapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListPersonsScreen(
    snackbarHostState: SnackbarHostState,
    navigate: (String) -> Unit,
    viewModel: ListPersonsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = { viewModel.refreshPersonsFacts() }
    )

    if (scrollState.value == scrollState.maxValue) {
        LaunchedEffect(Unit) {
            viewModel.addPersonsFacts()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (uiState.isLoading) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .pullRefresh(pullRefreshState)
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp)
                        .verticalScroll(scrollState)
                        .fillMaxSize(),
                ) {
                    Text(
                        text = stringResource(R.string.persons_page_title),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(20.dp)
                    )
                    uiState.persons.forEach { person ->
                        PersonItem(
                            person = person,
                            navigate = navigate
                        )
                    }
                }
                PullRefreshIndicator(
                    refreshing = uiState.isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }

    uiState.errorMsg?.let { userMessage ->
        LaunchedEffect(userMessage) {
            scope.launch {
                val result = snackbarHostState
                    .showSnackbar(
                        message = userMessage,
                        actionLabel = "Retry",
                        duration = SnackbarDuration.Short,
                    )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.refreshPersonsFacts()
                    }

                    SnackbarResult.Dismissed -> {}
                }
            }
            viewModel.userMessageShown()
        }
    }
}
