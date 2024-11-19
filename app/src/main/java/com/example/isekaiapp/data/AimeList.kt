package com.example.isekaiapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeList(
    @SerialName("data")
    val animes: MutableList<Anime>,
    var pagination: Pagination
)

@Serializable
data class Anime(
    @SerialName("mal_id")
    val malId: Int,
    val url: String,
    val synopsis: String?,
    val images: AnimeImage,
    val title: String,
)

@Serializable
data class AnimeImage(
    val jpg: JpgImage
)

@Serializable
data class JpgImage(
    @SerialName("image_url")
    val img: String
)

@Serializable
data class FullAnimeInfo(
    @SerialName("mal_id")
    val malId: Int,
    val images: AnimeImage,
    val title: String,
    val type: String?,
    val source: String?,
    val episodes: Int?,
    val status: String?,
    val rating: String?,
    val score: Float?,
    val background: String?,
    val synopsis: String?,
)

@Serializable
data class AnimeInfo(
    @SerialName("data")
    val anime: FullAnimeInfo
)


