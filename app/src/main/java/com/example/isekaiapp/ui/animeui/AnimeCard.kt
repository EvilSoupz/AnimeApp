package com.example.isekaiapp.ui.animeui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.isekaiapp.ui.theme.IsekaiAppTheme


@Composable
fun AnimeCard(
    animeName: String,
    animeDescription: String,
    animeImage: String,
    modifier: Modifier
) {
    var click by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
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
            Row(
                modifier = Modifier
                    .height(30.dp)
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)

                ) {
                    Text(
                        text = "More Info",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                TextButton(
                    onClick = { click = !click },
                    modifier = Modifier
                        .weight(1f)

                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun CardPreview() {
    IsekaiAppTheme(darkTheme = true) {
        AnimeCard(
            animeName = "TestAnime",
            animeDescription = "TestDescription",
            animeImage = "",
            modifier = Modifier
        )

    }

}