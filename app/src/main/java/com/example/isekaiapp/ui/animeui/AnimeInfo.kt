package com.example.isekaiapp.ui.animeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.isekaiapp.R
import com.example.isekaiapp.network.AnimeImage
import com.example.isekaiapp.network.FullAnimeInfo
import com.example.isekaiapp.network.JpgImage
import com.example.isekaiapp.ui.theme.IsekaiAppTheme

private val testAnimeInfo = FullAnimeInfo(
    malId = 0,
    title = "TestAnime",
    images = AnimeImage(JpgImage(img = "CARTINKA")),
    type = "type",
    source = "sourse",
    episodes = 0,
    status = " status",
    rating = "rating",
    score = 0f,
    background = "background"

)
@Composable
fun AnimeInfo(
    fullAnimeInfo: FullAnimeInfo,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = fullAnimeInfo.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        //заменить на AsyncImage
//        Image(
//            painter = painterResource(R.drawable.guren_laggan),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//
//        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(fullAnimeInfo.images.jpg.img)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)


        )
        Text(
            text = " id = ${fullAnimeInfo.malId}\n" +
                    "title = ${fullAnimeInfo.title}\n" +
                    "type = ${fullAnimeInfo.type}\n"+
                    "source = ${fullAnimeInfo.source}\n"+
                    "episodes = ${fullAnimeInfo.episodes}\n"+
                    "status = ${fullAnimeInfo.status}\n"+
                    "rating = ${fullAnimeInfo.rating}\n"+
                    "score = ${fullAnimeInfo.score}\n"+
                    "background = ${fullAnimeInfo.background}\n",
            textAlign = TextAlign.Center,
            modifier=Modifier
                .padding(top = 8.dp, start = 16.dp)
                .fillMaxWidth()
        )


    }
}

@Preview
@Composable
fun InfoPreview() {
    IsekaiAppTheme(darkTheme = false) {
        AnimeInfo(
            fullAnimeInfo = testAnimeInfo
        )

    }
}








