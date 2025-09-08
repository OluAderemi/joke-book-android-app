package com.example.jokebook

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jokebook.ui.theme.CategoryPage
import com.example.jokebook.ui.theme.JokeDetailPage

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "cover"
    ) {
        composable("cover") { CoverPage(navController) }

        composable("categories") { CategoryPage(navController) }

        composable("details/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0

            val jokes = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<List<com.example.jokebook.ui.theme.Joke>>("jokes")
                ?: emptyList()

            JokeDetailPage(
                jokes = jokes,
                startIndex = index,
                navController = navController
            )
        }
    }
}
