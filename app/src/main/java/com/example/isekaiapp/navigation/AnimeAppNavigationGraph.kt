package com.example.isekaiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.isekaiapp.viewmodel.AnimeInfoViewModel
import com.example.isekaiapp.viewmodel.AnimeListViewModel
import dagger.Binds
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@Composable
fun AnimeAppNavigationGraph(
    navHostController: NavHostController,
    mainScreenContent: @Composable (viewModel : AnimeListViewModel) -> Unit,
    fullAnimeInfoScreenContent: @Composable (viewModel: AnimeInfoViewModel) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen
    ) {
        composable<Screen.MainScreen> {
            val viewModel = hiltViewModel<AnimeListViewModel>()
            mainScreenContent(viewModel)
        }
        composable<Screen.FullAnimeInfoScreen> {
            val viewModel = hiltViewModel<AnimeInfoViewModel>(it)
            fullAnimeInfoScreenContent(viewModel)
        }
    }
}