package com.f4.starwarsfactsapp.ui.screens.listFacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.f4.starwarsfactsapp.data.model.PersonFacts
import com.f4.starwarsfactsapp.navigation.Routes
import com.f4.starwarsfactsapp.util.getIdFromUrl

@Composable
fun FactItem(
    fact: PersonFacts,
    navigate: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
        ),
        modifier = Modifier
            .padding(bottom = 10.dp)
            .clickable {
                navigate("${Routes.InfoFact.route}/${getIdFromUrl(fact.url)}")
            }
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = fact.name,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.width(200.dp),
            )
            Text(
                text = fact.birthYear,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.width(200.dp),
            )
        }
    }
}


