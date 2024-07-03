package com.f4.starwarsfactsapp.ui.screens.fact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.f4.starwarsfactsapp.R
import com.f4.starwarsfactsapp.data.model.PersonFacts

@Composable
fun SuccessScreen(
    fact: PersonFacts
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = fact.name,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 20.dp),
                    )
                    TextComponent(
                        header = stringResource(R.string.height), value = fact.height.toString()
                    )
                    TextComponent(
                        header = stringResource(R.string.mass), value = fact.mass.toString()
                    )
                    TextComponent(
                        header = stringResource(R.string.hair_color), value = fact.hairColor
                    )
                    TextComponent(
                        header = stringResource(R.string.skin_color), value = fact.skinColor
                    )
                    TextComponent(
                        header = stringResource(R.string.eye_color), value = fact.eyeColor
                    )
                    TextComponent(
                        header = stringResource(R.string.birth_year), value = fact.birthYear
                    )
                    TextComponent(
                        header = stringResource(R.string.gender), value = fact.gender
                    )
                    TextComponent(
                        header = stringResource(R.string.homeworld), value = fact.homeworld
                    )
                    TextComponent(
                        header = stringResource(R.string.films),
                        value = fact.films.joinToString(", ")
                    )
                    TextComponent(
                        header = stringResource(R.string.species),
                        value = fact.species.joinToString(", ")
                    )
                    TextComponent(
                        header = stringResource(R.string.vehicles),
                        value = fact.vehicles.joinToString(", ")
                    )
                    TextComponent(
                        header = stringResource(R.string.starships),
                        value = fact.starships.joinToString(", ")
                    )
                }
            }
        }
    }
}

@Composable
fun TextComponent(header: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Right
        )
    }
}
