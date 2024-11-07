package com.example.isekaiapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.isekaiapp.network.AnimeApi
import com.example.isekaiapp.network.FullAnimeInfo
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface InfoState {
    data class Success(val animeInfo: FullAnimeInfo) : InfoState
    data object Loading : InfoState
    data object Error : InfoState
}


class AnimeInfoViewModel(private val animeId : Int) : ViewModel(){

    var infoState : InfoState by mutableStateOf(InfoState.Loading)
        private set

    init {
        getFullAnimeInfo()
    }


    private fun getFullAnimeInfo() {
        viewModelScope.launch {
            infoState = try {
                val listResult = AnimeApi.retrofitService.getFullAnimeInfo(animeId)
                InfoState.Success(listResult.anime)
            } catch (e: IOException) {
                InfoState.Error
            }


        }

    }
}


class AnimeInfoViewModelFactory(private val animeId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeInfoViewModel(animeId) as T
    }
}