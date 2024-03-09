package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Movie App") },
                            colors = TopAppBarDefaults.topAppBarColors()
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            BottomNavigation {
                                BottomNavigationItem(
                                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                                    label = { Text("Home") },
                                    selected = true,
                                    onClick = { /* Handle click */ }
                                )
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = "Watchlist"
                                        )
                                    },
                                    label = { Text("Watchlist") },
                                    selected = false,
                                    onClick = { /* Handle click */ }
                                )
                            }
                        }
                    }

                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MovieList(getMovies())
                    }
                }
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(movies) { movie ->
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = movie.images.firstOrNull()),
                    contentDescription = "Movie Image",
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    AnimatedVisibility(expanded) {
                        Column {
                            Text(text = "Director: ${movie.director}")
                            Text(text = "Year: ${movie.year}")
                            Text(text = "Plot: ${movie.plot}")

                        }
                    }
                }
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
