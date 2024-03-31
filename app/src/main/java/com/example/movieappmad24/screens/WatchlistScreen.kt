package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(navController: NavController) {
    val watchlistMovies = getWatchlistMovies() // Your hardcoded list

    Scaffold(
        topBar = {
            SmallTopAppBar(title = { Text("Watchlist") })
        },
        bottomBar = {
            AppBottomBar(navController = navController)
        }
    ) { paddingValues ->
        // Now passing navController to MovieList
        MovieList(movies = watchlistMovies, navController = navController, paddingValues = paddingValues)
    }
}



@Composable
fun MovieList(movies: List<Movie>, paddingValues: PaddingValues,navController: NavController) {
    LazyColumn(contentPadding = paddingValues) {
        items(movies) { movie ->
            MovieRow(movie = movie, navController = navController) // Pass navController here
        }
    }
}

// Mock function to get movies - replace this with your actual data fetching logic
fun getWatchlistMovies(): List<Movie> {
    return listOf(
        Movie(
            id = "1",
            title = "Example Movie 1",
            year = "2010",
            genre = "Action, Adventure", // Genre as a single String
            director = "Director 1",
            actors = "Actor 1, Actor 2",
            plot = "This is an example plot for Movie 1.",
            images = listOf("https://example.com/movie1.jpg"), // List of image URLs
            trailer = "https://example.com/trailer1",
            rating = "PG-13"
        ),
        Movie(
            id = "2",
            title = "Example Movie 2",
            year = "2012",
            genre = "Drama, Thriller",
            director = "Director 2",
            actors = "Actor 3, Actor 4",
            plot = "This is an example plot for Movie 2.",
            images = listOf("https://example.com/movie2.jpg"),
            trailer = "https://example.com/trailer2",
            rating = "R"
        )
        // Add more movies as needed
    )
}



@Composable
fun AppBottomBar(navController: NavController) {
    val currentRoute = navController.currentDestination?.route
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == Screen.HomeScreen.route,
            onClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Watchlist") },
            label = { Text("Watchlist") },
            selected = currentRoute == Screen.WatchlistScreen.route,
            onClick = {
                navController.navigate(Screen.WatchlistScreen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
