package com.example.isekaiapp.ui.animeui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.isekaiapp.R
import com.example.isekaiapp.viewmodel.AnimeInfoViewModel
import com.example.isekaiapp.viewmodel.AnimeInfoViewModelFactory
import com.example.isekaiapp.viewmodel.InfoState

@Composable
fun AnimeInfoScreen(
    animeId : Int
) {

    val animeInfoViewModel : AnimeInfoViewModel = viewModel( factory = AnimeInfoViewModelFactory(animeId))
    when (val infoState = animeInfoViewModel.infoState) {
        is InfoState.Success -> {
            AnimeInfo(fullAnimeInfo = infoState.animeInfo)
        }
        is InfoState.Loading -> {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        }
        else -> {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )

        }
    }
    //

//    AnimeInfo(fullAnimeInfo = viewModel.getFullAnimeInfo(animeId))

}