package com.f4.starwarsfactsapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.navigation.NavType
import com.f4.starwarsfactsapp.ui.screens.fact.FactScreen
import com.f4.starwarsfactsapp.ui.screens.listFacts.ListFactsScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = START,
        enterTransition = { EnterTransition.None },
        /**
         *    Отключение анимаций перехода между экранами
         *       exitTransition = { ExitTransition.None },
         *       popEnterTransition = { EnterTransition.None },
         *       popExitTransition = { ExitTransition.None },
         */
    ) {
        navigation(
            route = START,
            startDestination = Routes.ListFacts.route

        ) {
            /** список фактов */
            composable(route = Routes.ListFacts.route) {
                ListFactsScreen()
            }
            /** информация о факте */
            composable(
                route = "${Routes.InfoFact.route}/{factId}",
                arguments = listOf(
                    navArgument(name = "profileId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                FactScreen(
                    factId = backStackEntry.arguments?.getInt("factId") ?: -1,
                )
            }
        }
    }
}