package com.example.isekaiapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/// https://docs.api.jikan.moe/#tag/anime/operation/getAnimeSearch


private val zxcJson = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(zxcJson.asConverterFactory("application/json".toMediaType()))
    .baseUrl("https://api.jikan.moe/")
    .build()


interface AnimeApiInterface {
    @GET("v4/anime")
    suspend fun getAnimeList(): AnimeList


    @GET("v4/anime/{id}/full")
    suspend fun getFullAnimeInfo(@Path("id")id : Int): AnimeInfo


}

object AnimeApi {
    val retrofitService: AnimeApiInterface by lazy {
        retrofit.create(AnimeApiInterface::class.java)
    }
}

@Serializable
data class AnimeList(
    @SerialName("data")
    val animes: List<Anime>
)

@Serializable
data class Anime(
    @SerialName("mal_id")
    val malId: Int,
    val synopsis: String,  // заменить на synopsis
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
    val malId: Int  = 1,
    val images: AnimeImage,
    val title: String = "",
    val type : String?= "",
    val source : String?="",
    val episodes : Int?=0,
    val status : String?="",
    val rating : String ?="",
    val score : Float?=0f,
    val background : String?=""
)

@Serializable
data class AnimeInfo(
    @SerialName("data")
    val anime : FullAnimeInfo
)







//interface AnimeApiInterface {
//    @GET("manga")
//    suspend fun getAnimeList(): MangaList
//}
//
//object AnimeApi {
//    val retrofitService: AnimeApiInterface by lazy {
//        retrofit.create(AnimeApiInterface::class.java)
//    }
//}
//@Serializable
//data class MangaList(
//    val result : String,
//    val response : String,
//    val data: List<Manga>,
//    val limit : Int,
//    val offset : Int,
//    val total : Int
//) {
//
//}
//@Serializable
//data class Manga(
//    val id: String,
//    val type: String,
//    val attributes : MangaAttributes
//)
//@Serializable
//data class MangaAttributes(
//    val title: Map<String,String>,
//    val description : Map<String,String>
//)
