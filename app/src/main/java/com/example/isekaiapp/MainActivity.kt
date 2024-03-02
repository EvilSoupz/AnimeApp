package com.example.isekaiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
//import com.example.isekaiapp.data.animeList
import com.example.isekaiapp.ui.theme.IsekaiAppTheme
import com.example.isekaiapp.viewmodel.AnimeState
import com.example.isekaiapp.viewmodel.AnimeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsekaiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimeApp()
                }
            }
        }
    }
}

@Composable
fun AnimeCard(
    animeName: String,
    animeDescription: String,
    animeImage : String,
    modifier: Modifier
) {
    var click by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .clickable { click = !click }
            .padding(8.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Text(
                text = animeName,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
//            Image(
//                painter = painterResource(R.drawable.guren_laggan),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(240.dp)
//            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(animeImage)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)

            )

            Spacer(modifier = Modifier.height(4.dp))
            if (click) {
                Text(
                    text = animeDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp)
                )
            }

        }
    }
}

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


//    LazyColumn() {
//        items(animeViewModel.animeList.data) {
//            AnimeCard(
//                mangaName = it.attributes.title["en"].toString(),
//                mangaDescription = it.attributes.description["en"].toString(),
//                modifier = Modifier
//            )
//        }
//    }
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

//    NewAnimeApp(viewModel = animeViewModel)

}

//@Composable
//fun NewAnimeApp(
//    viewModel: AnimeViewModel
//){
//
//    Column {
//        Text(text = viewModel.mangaTitle)
//        Text(text = viewModel.mangaDescription)
//    }
//}

@Preview
@Composable
fun GreetingPreview() {
    IsekaiAppTheme(darkTheme = false) {
        AnimeApp()
    }
}

@Preview
@Composable
fun GreetingPreviewDark() {
    IsekaiAppTheme(darkTheme = true) {
        AnimeApp()
    }
}