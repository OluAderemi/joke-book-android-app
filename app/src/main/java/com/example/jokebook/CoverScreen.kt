package com.example.jokebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.lifecycleScope
import com.example.jokebook.ui.theme.JokeBookTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CoverScreen : ComponentActivity() {
    private lateinit var themePrefs: ThemePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themePrefs = ThemePreferences(this)

        setContent {
            val navController = rememberNavController()

            var isDarkTheme by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                themePrefs.isDarkMode.collectLatest { savedDarkMode ->
                    isDarkTheme = savedDarkMode
                }
            }

            JokeBookTheme(isDarkTheme = isDarkTheme) {
                AppNavHost(
                    navController = navController,
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = {
                        isDarkTheme = !isDarkTheme
                        lifecycleScope.launch {
                            themePrefs.setDarkMode(isDarkTheme)
                        }
                    }
                )
            }
        }

    }
}
