package com.example.isekaiapp.navigation

import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
sealed class Screen(

) {
    @Serializable
    data object MainScreen : Screen()
    @Serializable
    data class FullAnimeInfoScreen (val animeId : Int) : Screen()

}