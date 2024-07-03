package com.f4.starwarsfactsapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
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
                ListFactsScreen(
                    navigate = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            /** информация о факте */
            composable(
                route = "${Routes.InfoFact.route}/{factId}",
                arguments = listOf(
                    navArgument(name = "factId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                FactScreen(
                    personId = backStackEntry.arguments?.getInt("factId") ?: -1,
                )
            }
        }
    }
}