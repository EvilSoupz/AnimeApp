package com.example.isekaiapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isekaiapp.data.AnimeList
import com.example.isekaiapp.data.PaginatingItems
import com.example.isekaiapp.data.Pagination
import com.example.isekaiapp.network.AnimeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


sealed interface AnimeState {
    data class Success(val animeList: AnimeList) : AnimeState
    data object Loading : AnimeState
    data object Error : AnimeState
}

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val animeApi : AnimeApi
) : ViewModel() {
    var animeState: AnimeState by mutableStateOf(AnimeState.Loading)
        private set

    private var animeList: AnimeList = AnimeList(
        animes = mutableListOf(), pagination = Pagination(
            lastVisiblePage = 0,
            currentPage = 0,
            hasNextPage = false,
            items = PaginatingItems(count = 0, total = 0, perPage = 0)
        )
    )
    private var question: String = ""

    init {
        getAnimeList()
    }

    private fun setQuestion(quest: String) {
        question = quest
    }

    fun resetQuestion() {
        question = ""
    }

    private fun resetAnimeList() {
        animeList = AnimeList(
            animes = mutableListOf(), pagination = Pagination(
                lastVisiblePage = 0,
                currentPage = 0,
                hasNextPage = false,
                items = PaginatingItems(count = 0, total = 0, perPage = 0)
            )
        )

    }

    fun getAnimeList(page: Int = 1) {
        animeState = AnimeState.Loading
        viewModelScope.launch {
            animeState = try {
                val listResult = animeApi.getAnimeList(page, question)
                animeList.animes += listResult.animes
                animeList.pagination = listResult.pagination
                AnimeState.Success(animeList)
            } catch (e: IOException) {
                AnimeState.Error
            }
        }
    }
    fun getAnimeListByq(q: String) {
        resetAnimeList()
        setQuestion(q)
        getAnimeList()
    }


}