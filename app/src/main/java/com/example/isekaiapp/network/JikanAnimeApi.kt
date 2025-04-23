package com.example.isekaiapp.network

import com.example.isekaiapp.data.AnimeInfo
import com.example.isekaiapp.data.AnimeList
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton


/// https://docs.api.jikan.moe/#tag/anime/operation/getAnimeSearch
////  https://api.jikan.moe/v4/anime?genres=62  жанр 62 = isekai



private val interceptor  = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}

private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

private val zxcJson = Json { ignoreUnknownKeys = true }


interface JikanAnimeApi {
//    @GET("v4/anime")
//    suspend fun getAnimeListByq(@Query("q") q: String): AnimeList    //q - поисковый запрос

    @GET("v4/anime")
    suspend fun getAnimeList(
        @Query("page") page: Int,
        @Query("q") q: String
    ): AnimeList   //v4/anime?page=xxxx

    @GET("v4/anime/{id}/full")
    suspend fun getFullAnimeInfo(@Path("id") id: Int): AnimeInfo
}


@Module
@InstallIn(SingletonComponent::class)
object AnimeApiModule {
    @Provides
    @Singleton
    fun provideAnimeApi(): JikanAnimeApi{
        return Retrofit.Builder()
            .addConverterFactory(zxcJson.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://api.jikan.moe/")
            .client(client)
            .build()
            .create(JikanAnimeApi::class.java)
    }
}







