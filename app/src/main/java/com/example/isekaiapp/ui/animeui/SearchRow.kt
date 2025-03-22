package com.example.isekaiapp.ui.animeui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SearchRow(
    onSearchButton: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier.fillMaxWidth()) {


        var textValue by remember {
            mutableStateOf("")
        }
        TextField(
            label = {
                Text(text = "Search")
            },
            value = textValue,
            onValueChange = { textValue = it },
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = {
                onSearchButton(textValue)
            },
        )
        {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
fun SearchRowPreview() {
    SearchRow(onSearchButton = {})
}