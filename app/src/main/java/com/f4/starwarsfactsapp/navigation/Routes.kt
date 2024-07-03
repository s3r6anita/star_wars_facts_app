package com.f4.starwarsfactsapp.navigation

import androidx.annotation.StringRes
import com.f4.starwarsfactsapp.R

const val START = "start"

sealed class Routes(
    val route: String,
    @StringRes val title: Int
) {
    data object ListFacts : Routes("ListFacts", R.string.list_title)
    data object InfoFact : Routes("InfoFact", R.string.info_title)
}