package com.example.jokebook.ui.theme

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun JokeDetailPage(
    navController: NavHostController,
    jokes: List<Joke>,
    startIndex: Int
) {
    var currentIndex by rememberSaveable { mutableStateOf(startIndex.coerceIn(jokes.indices)) }
    val currentJoke = jokes.getOrNull(currentIndex) ?: return

    val decodedSetup = URLDecoder.decode(currentJoke.setup, StandardCharsets.UTF_8.name())
    val decodedPunchline = URLDecoder.decode(currentJoke.punchline, StandardCharsets.UTF_8.name())

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount > 0 && currentIndex > 0) currentIndex--
                    if (dragAmount < 0 && currentIndex < jokes.lastIndex) currentIndex++
                }
            },
        color = Color(0xFFB3E5FC)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // Home Button
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

            // Joke content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 48.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                    Text("Back to List", color = Color(0xFF0A3D62))
                }
            }

            // Back Arrow
            if (currentIndex > 0) {
                IconButton(
                    onClick = { currentIndex-- },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Previous Joke",
                        tint = Color(0xFF0A3D62)
                    )
                }
            }

            // Forward Arrow
            if (currentIndex < jokes.lastIndex) {
                IconButton(
                    onClick = { currentIndex++ },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Next Joke",
                        tint = Color(0xFF0A3D62)
                    )
                }
            }
        }
    }
}
