package com.f4.starwarsfactsapp.ui.screens.persons.person.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.f4.starwarsfactsapp.R
import com.f4.starwarsfactsapp.data.model.Person

@Composable
fun PersonCard(
    person: Person,
    films: List<String>,
    homeworld: String
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), colors = CardDefaults.cardColors(
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
                text = person.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 20.dp),
            )
            TextComponent(
                header = stringResource(R.string.height), value = person.height
            )
            TextComponent(
                header = stringResource(R.string.mass), value = person.mass
            )
            TextComponent(
                header = stringResource(R.string.hair_color),
                value = person.hairColor
            )
            TextComponent(
                header = stringResource(R.string.skin_color),
                value = person.skinColor
            )
            TextComponent(
                header = stringResource(R.string.eye_color),
                value = person.eyeColor
            )
            TextComponent(
                header = stringResource(R.string.birth_year),
                value = person.birthYear
            )
            TextComponent(
                header = stringResource(R.string.gender), value = person.gender
            )
            TextComponent(
                header = stringResource(R.string.homeworld),
                value = homeworld
            )
            TextComponent(
                header = stringResource(R.string.films),
                value = films.joinToString(",\n")
            )
            // у людей вид не указан
//            if (person.species.isNotEmpty()) {
//                TextComponent(
//                    header = stringResource(R.string.species),
//                    value = person.species.joinToString(", ")
//                )
//            }
//            TextComponent(
//                header = stringResource(R.string.vehicles),
//                value = person.vehicles.joinToString(", ")
//            )
//            TextComponent(
//                header = stringResource(R.string.starships),
//                value = person.starships.joinToString(", ")
//            )
        }
    }
}

