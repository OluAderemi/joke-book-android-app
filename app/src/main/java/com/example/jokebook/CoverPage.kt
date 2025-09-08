package com.example.jokebook

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CoverPage(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFADD8E6)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Joke Book",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("categories") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFADD8E6)
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Enter",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
