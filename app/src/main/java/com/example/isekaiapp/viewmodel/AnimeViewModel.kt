package com.example.isekaiapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isekaiapp.network.AnimeApi
import com.example.isekaiapp.network.AnimeList
import com.example.isekaiapp.network.FullAnimeInfo
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AnimeState {
    data class Success(val animeList: AnimeList) : AnimeState
    data object Loading : AnimeState
    data object Error : AnimeState
}
sealed interface InfoState {
    data class Success(val animeInfo: FullAnimeInfo) : InfoState
    data object Loading : InfoState
    data object Error : InfoState
}




class AnimeViewModel : ViewModel() {
    var animeState: AnimeState by mutableStateOf(AnimeState.Loading)
        private set


    var infoState : InfoState by mutableStateOf(InfoState.Loading)

    var animeId: Int? by mutableStateOf(1)

    init {
        getAnimeList()
    }

    fun setAnimeId(id: Int) {
        animeId = id
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

    fun getFullAnimeInfo() {
//        var animeinfo: FullAnimeInfo? = null
        viewModelScope.launch {





            if (animeId!=null){
                infoState = try {
                    val listResult = AnimeApi.retrofitService.getFullAnimeInfo(animeId!!)
                    InfoState.Success(listResult.anime)
                } catch (e: IOException) {
                    InfoState.Error
                }
            }






//            animeState = try {
//                val resultanime = AnimeApi.retrofitService.getFullAnimeInfo(animeId)
//                AnimeState.SuccesAnime(resultanime)
//            } catch (e: IOException){
//                AnimeState.Error
//            }
//            if (animeId != null) {
//                try {
//                    println("test123 : lox1")
//                    animeinfo = AnimeApi.retrofitService.getFullAnimeInfo(1).anime
//                    println("test123 : $animeinfo")
//                } catch (e: IOException) {
//                }
//            } else {
//                try {
//                    println("test123 : lox2")
//                    animeinfo = AnimeApi.retrofitService.getFullAnimeInfo(1).anime
//                    println("test123 : $animeinfo")
//                } catch (e: IOException) {
//                }
//            }
//
//
//        }
//        return animeinfo!!
        }

    }


}