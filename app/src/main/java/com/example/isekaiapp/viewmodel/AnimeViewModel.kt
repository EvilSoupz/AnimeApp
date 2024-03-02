package com.example.isekaiapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isekaiapp.network.AnimeApi
import com.example.isekaiapp.network.AnimeList
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AnimeState {
    data class Success(val animeList: AnimeList) : AnimeState
    data object Loading : AnimeState
    data object Error : AnimeState
}

class AnimeViewModel : ViewModel() {
    var animeState: AnimeState by mutableStateOf(AnimeState.Loading)
        private set

    init {
        getAnimeList()
    }

    private fun getAnimeList() {
        viewModelScope.launch {
            animeState = try {
                val listResult = AnimeApi.retrofitService.getAnimeList()
                AnimeState.Success(listResult)
            } catch (e: IOException) {
                AnimeState.Error
            }
        }


    }


}