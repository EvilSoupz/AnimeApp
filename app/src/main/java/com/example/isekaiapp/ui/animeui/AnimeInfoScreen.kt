package com.example.isekaiapp.ui.animeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.isekaiapp.R
import com.example.isekaiapp.viewmodel.AnimeState
import com.example.isekaiapp.viewmodel.AnimeViewModel
import com.example.isekaiapp.viewmodel.InfoState

@Composable
fun AnimeInfoScreen(
    infoState: InfoState,
) {
    when (infoState) {
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