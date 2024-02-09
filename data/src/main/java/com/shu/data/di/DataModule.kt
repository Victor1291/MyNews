package com.shu.data.di

import android.app.Application
import androidx.room.Room
import com.shu.data.roomDb.RoomRepositoryImpl
import com.shu.data.roomDb.AppDataBasePhoto
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesAppDataBasePhoto (application: Application): AppDataBasePhoto {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDataBasePhoto::class.java,
            "app_db_photo"
        ).build()
    }

    @Provides
    fun provideRoomRepository (appDataBasePhoto: AppDataBasePhoto): RoomRepositoryImpl {
        return RoomRepositoryImpl(appDataBasePhoto.photoDao())
    }
}