package com.example.isekaiapp.ui.animeui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.navigation.compose.rememberNavController
import com.example.isekaiapp.R
import com.example.isekaiapp.navigation.AnimeAppNavigationGraph
import com.example.isekaiapp.navigation.Screen



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

    val navController = rememberNavController()
    Scaffold(
        topBar = { TopAnimeBar() }
    ) { it ->
        Column(
            modifier = Modifier.padding(it)
        ) {


            AnimeAppNavigationGraph(
                navHostController = navController,
                mainScreenContent = {
                                    AnimeList(
                                        onInfoButton = {
                                            navController.navigate(Screen.FullAnimeInfoScreen(it))
                                        }
                                    )


                },
                fullAnimeInfoScreenContent = {
                    
                         AnimeInfoScreen(it)

                }
            )

        }
    }

}

