package com.shu.mynews.di

import com.shu.data.roomDb.RoomRepositoryImpl
import com.shu.domain.RoomRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    fun bindRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository

}