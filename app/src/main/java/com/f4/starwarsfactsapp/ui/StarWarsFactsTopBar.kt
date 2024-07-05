package com.f4.starwarsfactsapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarWarsFactsTopBar(
    navigateUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "") },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        }
    )
}