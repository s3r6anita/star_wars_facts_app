package com.f4.starwarsfactsapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.f4.starwarsfactsapp.ui.screens.persons.list.ListPersonsScreen
import com.f4.starwarsfactsapp.ui.screens.persons.person.PersonFactsScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = START,
        enterTransition = { EnterTransition.None },
        /**
         *    Отключение анимаций переходов между экранами
         *       exitTransition = { ExitTransition.None },
         *       popEnterTransition = { EnterTransition.None },
         *       popExitTransition = { ExitTransition.None },
         */
    ) {
        navigation(
            route = START,
            startDestination = Routes.ListFacts.route

        ) {
            /** список персонажей */
            composable(route = Routes.ListFacts.route) {
                ListPersonsScreen(
                    snackbarHostState = remember { SnackbarHostState() },
                    navigate = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            /** информация персонаже */
            composable(
                route = "${Routes.InfoFact.route}/{personId}",
                arguments = listOf(
                    navArgument(name = "personId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                PersonFactsScreen(
                    personId = backStackEntry.arguments?.getInt("personId") ?: -1,
                    snackbarHostState = remember { SnackbarHostState() },
                    navigateUp = { navController.navigateUp() },
                )
            }
        }
    }
}