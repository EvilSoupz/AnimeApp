package com.example.isekaiapp.network

import android.text.BoringLayout
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


    @GET("v4/anime")
    suspend fun getAnimeList(@Query("page")page : Int) : AnimeList   //v4/anime?page=xxxx


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
    val animes: MutableList<Anime>,
    var pagination: Pagination

)



@Serializable
data class Anime(
    @SerialName("mal_id")
    val malId: Int,
    val synopsis: String,
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
    val malId: Int  ,
    val images: AnimeImage,
    val title: String ,
    val type : String?,
    val source : String?,
    val episodes : Int?,
    val status : String?,
    val rating : String ?,
    val score : Float?,
    val background : String?,
    val synopsis: String,
)

@Serializable
data class AnimeInfo(
    @SerialName("data")
    val anime : FullAnimeInfo
)






