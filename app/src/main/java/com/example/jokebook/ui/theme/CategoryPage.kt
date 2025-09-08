package com.example.jokebook.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URLEncoder
import java.net.URL

data class Joke(
    val type: String,
    val setup: String,
    val punchline: String,
    val id: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryPage(navController: NavHostController) {
    val categories = listOf("general", "knock-knock", "programming")

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var jokes by remember { mutableStateOf<List<Joke>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(selectedCategory) {
        selectedCategory?.let { cat ->
            isLoading = true
            error = null
            jokes = try {
                fetchJokes(cat)
            } catch (e: Exception) {
                error = e.localizedMessage ?: "Failed to load jokes."
                emptyList()
            }
            isLoading = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFADD8E6)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pick a Joke Category",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                categories.forEach { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { selectedCategory = cat },
                        label = { Text(cat.replaceFirstChar { it.uppercase() }) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color.White,
                            selectedLabelColor = Color(0xFFADD8E6),
                            containerColor = Color.White.copy(alpha = 0.8f),
                            labelColor = Color(0xFFADD8E6)
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) { CircularProgressIndicator(color = Color.White) }
                }
                error != null -> {
                    Text(
                        text = error ?: "",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                selectedCategory == null -> {
                    Text(
                        text = "Pick a category to load jokes.",
                        color = Color.White
                    )
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(jokes) { joke ->
                            Surface(
                                tonalElevation = 2.dp,
                                shape = MaterialTheme.shapes.medium,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            val setupEncoded = URLEncoder.encode(joke.setup, "UTF-8")
                                            val punchEncoded = URLEncoder.encode(joke.punchline, "UTF-8")
                                            navController.navigate("details/$setupEncoded/$punchEncoded")
                                        }
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = joke.setup,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color(0xFF0A3D62),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

suspend fun fetchJokes(category: String): List<Joke> = withContext(Dispatchers.IO) {
    val endpoint = "https://official-joke-api.appspot.com/jokes/$category/ten"
    val url = URL(endpoint)
    val conn = (url.openConnection() as HttpURLConnection).apply {
        connectTimeout = 10_000
        readTimeout = 10_000
        requestMethod = "GET"
    }
    conn.inputStream.use { input ->
        val json = input.bufferedReader().readText()
        val arr = JSONArray(json)
        List(arr.length()) { i ->
            val o = arr.getJSONObject(i)
            Joke(
                type = o.optString("type"),
                setup = o.optString("setup"),
                punchline = o.optString("punchline"),
                id = o.optInt("id")
            )
        }
    }
}
