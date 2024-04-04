package com.example.isekaiapp.ui.animeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.isekaiapp.R
import com.example.isekaiapp.viewmodel.AnimeState

@Composable
fun AnimeList(
    animeState: AnimeState,
    onInfoButton: (Int)-> Unit

) {

    when (animeState) {
        is AnimeState.Success -> {
            LazyColumn() {
                items(animeState.animeList.animes) {
                    AnimeCard(
                        animeName = it.title,
                        animeDescription = it.synopsis,  //найти описание
                        animeImage = it.images.jpg.img,
                        onInfoButton = {onInfoButton(it.malId)},  //навигация на страницу с инфо
                        modifier = Modifier
                    )
                }
            }
        }
        is AnimeState.Loading -> {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        }


        is AnimeState.Error -> {
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



}