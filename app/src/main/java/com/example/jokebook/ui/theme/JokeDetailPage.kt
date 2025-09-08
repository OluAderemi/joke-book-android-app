package com.example.jokebook.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun JokeDetailPage(
    setup: String,
    punchline: String,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFB3E5FC)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = { navController.navigate("cover") },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = Color(0xFF0A3D62)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val decodedSetup = URLDecoder.decode(setup, StandardCharsets.UTF_8.name())
                val decodedPunchline = URLDecoder.decode(punchline, StandardCharsets.UTF_8.name())

                Text(
                    text = decodedSetup,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF0A3D62)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = decodedPunchline,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF0A3D62)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Back to List",
                        color = Color(0xFF0A3D62)
                    )
                }
            }
        }
    }
}
