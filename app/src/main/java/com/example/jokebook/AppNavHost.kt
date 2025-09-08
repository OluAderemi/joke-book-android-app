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
        composable("details/{setup}/{punchline}") { backStackEntry ->
            val setup = backStackEntry.arguments?.getString("setup") ?: ""
            val punchline = backStackEntry.arguments?.getString("punchline") ?: ""
            JokeDetailPage(setup, punchline, navController)
        }
    }
}
