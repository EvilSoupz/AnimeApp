package com.example.isekaiapp.data

import com.example.isekaiapp.network.JikanAnimeApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject
import javax.inject.Singleton

interface AnimeRepository {

    suspend fun getAnimeList(page: Int, query: String): AnimeList

    suspend fun getFullAnimeInfo(id: Int): AnimeInfo

}

@Singleton
class OnlineRepository @Inject constructor(
    private val animeApi: JikanAnimeApi
) : AnimeRepository {
    override suspend fun getAnimeList(page: Int, query: String): AnimeList =
        animeApi.getAnimeList(page, query)

    override suspend fun getFullAnimeInfo(id: Int): AnimeInfo = animeApi.getFullAnimeInfo(id)

}


@Module
@InstallIn(ViewModelComponent::class)
abstract class OnlineRepositoryModule() {
    @Binds
    abstract fun bindsAnimeRepository(onlineRepository: OnlineRepository): AnimeRepository
}