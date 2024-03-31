package com.example.movieappmad24.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object HomeScreen : Screen("home", Icons.Default.Home, "Home")
    object DetailScreen : Screen("detail/{movieId}", Icons.Default.List, "Detail") {
        fun createRoute(movieId: String) = "detail/$movieId"
    }
    object WatchlistScreen : Screen("watchlist", Icons.Default.Star, "Watchlist")
}
