package com.f4.starwarsfactsapp.ui.screens.listFacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.f4.starwarsfactsapp.data.model.PersonFacts

@Composable
fun SuccessScreen(
    facts: List<PersonFacts>,
    navigate: (String) -> Unit
) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                facts.forEach { fact ->
                    FactItem(
                        fact = fact,
                        navigate = navigate
                    )
                }
            }
        }
    }
}
