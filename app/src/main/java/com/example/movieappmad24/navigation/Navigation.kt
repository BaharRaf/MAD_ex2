package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen
import com.example.movieappmad24.navigation.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(
                movieId = backStackEntry.arguments?.getString("movieId"),
                navController = navController
            )
        }

        composable(Screen.WatchlistScreen.route) {
            WatchlistScreen(navController = navController)
        }

    }
}