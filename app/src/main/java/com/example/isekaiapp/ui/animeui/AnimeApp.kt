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
import com.example.isekaiapp.R
import com.example.isekaiapp.viewmodel.AnimeState
import com.example.isekaiapp.viewmodel.AnimeViewModel


@Composable
fun AnimeList(
    animeState: AnimeState

) {

    when (animeState) {
        is AnimeState.Success -> {
            LazyColumn() {
                items(animeState.animeList.animes) {
                    AnimeCard(
                        animeName = it.title,
                        animeDescription = it.images.jpg.img,
                        animeImage = it.images.jpg.img,
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
    }



}

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
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            TopAnimeBar()
            AnimeList(animeState = animeViewModel.animeState)

        }
    }

}

