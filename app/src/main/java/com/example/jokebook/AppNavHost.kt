package com.example.jokebook

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "cover"
    ) {
        composable("cover") {
            CoverPage(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle
            )
        }

        composable("categories") {
            CategoryPage(
                navController = navController,
                isDarkTheme = isDarkTheme
            )
        }

        composable("details/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
            val jokes = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<List<Joke>>("jokes")
                ?: emptyList()

            JokeDetailPage(
                navController = navController,
                jokes = jokes,
                startIndex = index,
                isDarkTheme = isDarkTheme
            )
        }
    }
}
