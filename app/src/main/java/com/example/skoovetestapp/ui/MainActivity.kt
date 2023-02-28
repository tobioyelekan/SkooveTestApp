package com.example.skoovetestapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skoovetestapp.ui.screens.all_songs.AllSongScreen
import com.example.skoovetestapp.ui.theme.SkooveTestAppTheme
import com.example.skoovetestapp.util.Routes.songDetailsScreen
import com.example.skoovetestapp.util.Routes.songListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkooveTestAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = songListScreen
                ) {
                    composable(songListScreen) {
                        AllSongScreen(
                            navController = navController
                        )
                    }

                    composable(songDetailsScreen) {

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SkooveTestAppTheme {
        Greeting("Android")
    }
}