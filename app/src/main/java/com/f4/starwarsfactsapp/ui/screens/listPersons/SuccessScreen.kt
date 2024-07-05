package com.f4.starwarsfactsapp.ui.screens.listPersons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.f4.starwarsfactsapp.R
import com.f4.starwarsfactsapp.util.getPageFromUrl

@Composable
fun SuccessScreen(
    navigate: (String) -> Unit,
    viewModel: ListFactsViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val persons by viewModel.persons.collectAsState()
    val next by viewModel.next.collectAsState()

    if (scrollState.value == scrollState.maxValue && next != null) {
        LaunchedEffect(Unit) {
            viewModel.addPeopleFacts(page = getPageFromUrl(next!!))
        }
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
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
                persons.forEach { person ->
                    FactItem(
                        person = person,
                        navigate = navigate
                    )
                }
            }
        }
    }
}
