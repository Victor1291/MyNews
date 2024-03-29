package com.shu.data.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shu.data.api.NewsApi
import com.shu.data.api.NewsListRepositoryImpl
import com.shu.data.api.sun.api.ApiSun
import com.shu.data.collections.CollectionRepositoryImpl
import com.shu.data.habits.HabitsRepositoryImpl
import com.shu.data.roomDb.AppDataBasePhoto
import com.shu.data.roomDb.RoomRepositoryImpl
import com.shu.domain.collection.repository.CollectionRepository
import com.shu.domain.habits.HabitsRepository
import com.shu.domain.news.NewsListRepository
import com.shu.indianews.utils.Constants
import com.shu.indianews.utils.Constants.Companion.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class DataModule {

    @Provides
    fun provideRetrofit() : NewsApi = Retrofit
    .Builder()
    .client(
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }).build()
    )
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NewsApi::class.java)

    @Provides
    fun provideSunRetrofit() : ApiSun = Retrofit
        .Builder()
        .client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).build()
        )
        .baseUrl("https://api.sunrise-sunset.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiSun::class.java)


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

    @Provides
    fun provideHabitsRepository (appDataBasePhoto: AppDataBasePhoto): HabitsRepository {
        return HabitsRepositoryImpl(appDataBasePhoto.habitsDao())
    }

    @Provides
    fun provideCollectionRepository (appDataBasePhoto: AppDataBasePhoto): CollectionRepository {
        return CollectionRepositoryImpl(appDataBasePhoto.osDao())
    }

}