package com.example.isekaiapp.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun AnimeAppNavigationGraph(
    navHostController: NavHostController,
    mainScreenContent : @Composable ()->Unit,
    fullAnimeInfoScreenContent : @Composable (id : Int) -> Unit


) {


    NavHost(
        navController = navHostController,
        startDestination =  Screen.MainScreen
        ) {

            composable<Screen.MainScreen>{
                mainScreenContent()
            }
        composable <Screen.FullAnimeInfoScreen>{
            val screen: Screen.FullAnimeInfoScreen = it.toRoute()
            fullAnimeInfoScreenContent(screen.id)


        }


    }

}