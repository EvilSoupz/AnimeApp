package com.example.isekaiapp.ui.animeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    when (val animeState = animeListViewModel.animeState) {
        is AnimeState.Success -> {

            LazyColumn() {
                items(animeState.animeList.animes) {

                    AnimeCard(
                        animeName = it.title,
                        animeDescription = it.synopsis,
                        animeImage = it.images.jpg.img,
                        onInfoButton = { onInfoButton(it.malId) },  //навигация на страницу с инфо
                        modifier = Modifier
                    )
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val currentPage = animeState.animeList.pagination.currentPage
                        val hasNext = animeState.animeList.pagination.hasNextPage
                        if (currentPage > 1) {
                            Button(
                                onClick = {
                                    animeListViewModel.getAnimeList(currentPage - 1)
                                }
                            ) {
                                Text(text = "Previos Page")
                            }
                        } else {
                            Spacer(modifier = Modifier)
                        }
                        if (hasNext == true) {
                            Button(
                                onClick = {
                                    animeListViewModel.getAnimeList(currentPage + 1)
                                }
                            ) {
                                Text(text = "Next Page")
                            }
                        } else {
                            Spacer(modifier = Modifier)
                        }


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