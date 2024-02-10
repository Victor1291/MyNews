package com.shu.mynews.di

import com.shu.data.api.NewsListRepositoryImpl
import com.shu.data.roomDb.RoomRepositoryImpl
import com.shu.domain.RoomRepository
import com.shu.domain.news.NewsListRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    fun bindRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository


    @Binds
    fun bindNewsListRepository(newsListRepositoryImpl: NewsListRepositoryImpl): NewsListRepository

}