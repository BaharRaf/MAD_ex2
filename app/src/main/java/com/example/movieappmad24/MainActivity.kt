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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.ui.theme.Purple40
import com.example.movieappmad24.ui.theme.Purple80
import com.example.movieappmad24.ui.theme.PurpleGrey40
import com.example.movieappmad24.ui.theme.PurpleGrey80


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    "Movie App",
                                    color = Purple40 // Font color for title
                                ) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Purple80
                            )
                        )
                    },
                    bottomBar = {
                        BottomAppBar(containerColor = PurpleGrey80) {
                            BottomNavigation(
                                backgroundColor = PurpleGrey80,
                                contentColor = PurpleGrey40
                            ) {
                                BottomNavigationItem(
                                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home", tint = PurpleGrey40) },
                                    label = {
                                        Text(
                                            "Home",
                                            color = PurpleGrey40 // Font color for label
                                        )
                                    },
                                    selected = true,
                                    onClick = { /* Handle click */ }
                                )
                                BottomNavigationItem(
                                    icon = { Icon(Icons.Filled.Star, contentDescription = "Watchlist", tint = PurpleGrey40) },
                                    label = {
                                        Text(
                                            "Watchlist",
                                            color = PurpleGrey40 // Font color for label
                                        )
                                    },
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
                            .padding(paddingValues)
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
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey80), // Movie box background
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = movie.images.firstOrNull()),
                    contentDescription = "Movie Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopEnd),
                    contentScale = ContentScale.Crop // Adjust content scale to ensure proper fitting
                )
                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Toggle Details"
                    )
                }
            }
            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Director: ${movie.director}")
                    Text(text = "Released: ${movie.year}")
                    Text(text = "Genre: ${movie.genre}")
                    Text(text = "Actors: ${movie.actors}")
                    Text(text = "Rating: ${movie.rating}")
                    // Divider before the plot
                    Divider(
                        color = PurpleGrey40, // Set divider color
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(text = "Plot: ${movie.plot}")
                }
            }
        }
    }
}