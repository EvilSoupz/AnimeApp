package com.example.isekaiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.isekaiapp.ui.animeui.AnimeApp
import com.example.isekaiapp.ui.theme.IsekaiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsekaiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimeApp()
                }
            }
        }
    }
}


@Preview
@Composable
fun GreetingPreview() {
    IsekaiAppTheme(darkTheme = false) {
        AnimeApp()
    }
}

@Preview
@Composable
fun GreetingPreviewDark() {
    IsekaiAppTheme(darkTheme = true) {
        AnimeApp()
    }
}