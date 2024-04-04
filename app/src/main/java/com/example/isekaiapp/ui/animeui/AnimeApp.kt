package com.example.isekaiapp.ui.animeui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.isekaiapp.R
import com.example.isekaiapp.viewmodel.AnimeState
import com.example.isekaiapp.viewmodel.AnimeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAnimeBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )
        })
}

@Composable
fun AnimeApp() {
    val animeViewModel: AnimeViewModel = viewModel()
    val navController = rememberNavController()
    Scaffold { it ->
        Column(
            modifier = Modifier.padding(it)
        ) {
            TopAnimeBar()
            NavHost(
                navController = navController,
                startDestination = "animelist"
            ) {
                composable(
                    "animelist",
                ) {
                    AnimeList(animeState = animeViewModel.animeState, onInfoButton = {
                        animeViewModel.setAnimeId(it)
                        animeViewModel.getFullAnimeInfo()
                        navController.navigate("animeinfo")
                    }
                    )
                }
                composable("animeinfo") {
                    AnimeInfoScreen(
                        infoState = animeViewModel.infoState


                    )
                }
            }
//            AnimeList(animeState = animeViewModel.animeState)

        }
    }

}

