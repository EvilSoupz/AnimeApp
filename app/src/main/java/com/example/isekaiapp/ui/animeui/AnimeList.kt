package com.example.isekaiapp.ui.animeui

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.isekaiapp.R
import com.example.isekaiapp.viewmodel.AnimeListViewModel
import com.example.isekaiapp.viewmodel.AnimeState

@Composable
fun AnimeList(
    onInfoButton: (Int) -> Unit

) {


    val animeListViewModel: AnimeListViewModel = viewModel()
    val lazyColumState = rememberLazyListState()
    when (val animeState = animeListViewModel.animeState) {
        is AnimeState.Success -> {

            Box {
                LazyColumn(
                    state = lazyColumState
                ) {

                    items(animeState.animeList.animes, key = { it.malId }) {

                        AnimeCard(
                            animeName = it.title,
                            animeDescription = it.synopsis,
                            animeImage = it.images.jpg.img,
                            onInfoButton = { onInfoButton(it.malId) },
                            modifier = Modifier
                        )
                    }
                    val currentPage = animeState.animeList.pagination.currentPage
                    val hasNext = animeState.animeList.pagination.hasNextPage
                    if (!lazyColumState.canScrollForward && hasNext) {
                        animeListViewModel.getAnimeList(currentPage + 1)

                    }

                }
                if (lazyColumState.canScrollBackward)
                    IconButton(
                        onClick = { lazyColumState.requestScrollToItem(0) }
                    ) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
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
    }


}