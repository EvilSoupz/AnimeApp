package com.example.isekaiapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.isekaiapp.R

data class Anime(
    @StringRes val name : Int,
    @StringRes val description : Int,
    @DrawableRes val image : Int
)

//val animeList = listOf(
//    Anime(R.string.the_eminance_in_shadow,R.string.the_eminance_in_shadow_description,R.drawable.the_eminence_in_shadow),
//    Anime(R.string.gurren_lagan,R.string.guren_lagan_description,R.drawable.guren_laggan),
//    Anime(R.string.haven_illusion,R.string.haven_illusion_description,R.drawable.haven_illusion),
//)

