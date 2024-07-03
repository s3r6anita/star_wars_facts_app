package com.f4.starwarsfactsapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.f4.starwarsfactsapp.navigation.SetupNavGraph
import com.f4.starwarsfactsapp.ui.theme.StarWarsFactsAppTheme

@Composable
fun StarWarsFactsApp() {
    StarWarsFactsAppTheme {
        SetupNavGraph(
            navController = rememberNavController(),
        )
    }
}