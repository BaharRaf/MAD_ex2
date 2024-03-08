package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import coil.compose.rememberImagePainter


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Movie App") },
                            colors = TopAppBarDefaults.topAppBarColors()
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            // This is a placeholder. You can add icons/actions here as per your design.
                        }
                    }
                ) { paddingValues ->

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues), // Apply padding from Scaffold
                        color = MaterialTheme.colorScheme.background
                    ) {
                        // Use a Box to maintain the original structure while allowing for padding application
                        Box(modifier = Modifier.fillMaxSize()) {

                            Card {
                                // Example movie image loading with Coil; replace with actual data or dynamic content as needed
                                Image(
                                    painter = rememberImagePainter("https://example.com/movie_image.jpg"),
                                    contentDescription = "Movie Image"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}