package com.example.isekaiapp.ui.animeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.isekaiapp.R
import com.example.isekaiapp.viewmodel.AnimeListViewModel
import com.example.isekaiapp.viewmodel.AnimeState

@Composable
fun AnimeList(
    animeListViewModel: AnimeListViewModel,
    onInfoButton: (Int) -> Unit
) {

    val lazyColumState = rememberLazyListState()
    when (val animeState = animeListViewModel.animeState) {
        is AnimeState.Success -> {

            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    state = lazyColumState,
                    contentPadding = PaddingValues(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    item {
                        SearchRow(
                            onSearchButton = { animeListViewModel.getAnimeListByq(it) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    items(animeState.animeList.animes) {

                        AnimeCard(
                            animeName = it.title,
                            animeDescription = it.synopsis ?: "no info",
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

                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        IconButton(
                            onClick = { lazyColumState.requestScrollToItem(0) },
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null
                            )
                        }
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