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


class AnimeListViewModel : ViewModel() {
    var animeState: AnimeState by mutableStateOf(AnimeState.Loading)
        private set


    init {
        getAnimeList()
    }


    fun getAnimeList(page: Int = 1) {
        animeState = AnimeState.Loading
        viewModelScope.launch {
            animeState = try {
                val listResult = AnimeApi.retrofitService.getAnimeList(page)
                AnimeState.Success(listResult)
            } catch (e: IOException) {
                AnimeState.Error
            }
        }
    }


}