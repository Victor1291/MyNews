package com.shu.mynews.di

import com.shu.data.api.NewsListRepositoryImpl
import com.shu.data.api.sun.RepositoryImpl
import com.shu.data.collections.CollectionRepositoryImpl
import com.shu.data.roomDb.RoomRepositoryImpl
import com.shu.domain.RoomRepository
import com.shu.domain.news.NewsListRepository
import com.shu.domain.collection.repository.CollectionRepository
import com.shu.domain.collection.repository.Repository
import com.shu.mynews.ui.repository.HabitsMemoryCache
import com.shu.mynews.ui.repository.HabitsMemoryCacheImpl
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    fun bindRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository


    @Binds
    fun bindNewsListRepository(newsListRepositoryImpl: NewsListRepositoryImpl): NewsListRepository

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    fun bindHabitsMemoryCache(habitsMemoryCacheImpl: HabitsMemoryCacheImpl): HabitsMemoryCache

}