package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.viewmodels.MoviesViewModel
import com.example.movieappmad24.widgets.HorizontalScrollableImageView
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun DetailScreen(
    movieId: String?,
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    // Use the movieId to get the corresponding movie from the ViewModel
    movieId?.let { id ->
        moviesViewModel.movies.find { movie -> movie.id == id }?.let { movie ->
            Scaffold(
                topBar = {
                    SimpleTopAppBar(title = movie.title) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Go back"
                            )
                        }
                    }
                }
            ) { innerPadding ->
                Column {
                    // Pass the necessary lambda functions
                    MovieRow(
                        modifier = Modifier.padding(innerPadding),
                        movie = movie,
                        onFavoriteClick = { movieId ->
                            moviesViewModel.toggleFavoriteMovie(movieId)
                        },
                        onItemClick = { movieId ->
                            // Here, onItemClick could perhaps handle a different navigation or action
                            // Since it's a detail screen, it might not navigate anywhere.
                        }
                    )
                    HorizontalScrollableImageView(movie = movie)
                }
            }
        }
    }
}
