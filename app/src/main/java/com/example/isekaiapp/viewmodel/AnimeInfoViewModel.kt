package com.example.isekaiapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.isekaiapp.data.FullAnimeInfo
import com.example.isekaiapp.navigation.Screen
import com.example.isekaiapp.network.AnimeApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.isActive

import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


sealed interface InfoState {
    data class Success(val animeInfo: FullAnimeInfo) : InfoState
    data object Loading : InfoState
    data object Error : InfoState
}

@HiltViewModel
class AnimeInfoViewModel @Inject constructor(
    private val animeApi: AnimeApi,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var infoState: InfoState by mutableStateOf(InfoState.Loading)
        private set

    init{
       val  animeId = savedStateHandle.toRoute<Screen.FullAnimeInfoScreen>().animeId
            getFullAnimeInfo(animeId)
    }

    private fun getFullAnimeInfo(id : Int) {
        viewModelScope.launch {
            infoState = try {
                val listResult = animeApi.getFullAnimeInfo(id)
                InfoState.Success(listResult.anime)
            } catch (e: IOException) {
                InfoState.Error
            }
        }
    }
}
