package com.shu.mynews.di

import com.shu.data.roomDb.RoomRepositoryImpl
import com.shu.domain.GetAllPhotoUseCase
import com.shu.domain.RoomRepository
import com.shu.domain.SaveTakenPhotoUseCase
import com.shu.mynews.ui.gallery.FirstViewModel
import com.shu.mynews.ui.gallery.FirstViewModelFactory
import com.shu.mynews.ui.mainFragment.MainViewModel
import com.shu.mynews.ui.mainFragment.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    fun provideSaveTakenPhotoUseCase (repository: RoomRepository) : SaveTakenPhotoUseCase {
        return SaveTakenPhotoUseCase(repository)
    }

    @Provides
    fun provideMainViewModel (saveTakenPhotoUseCase: SaveTakenPhotoUseCase): MainViewModel {
        return  MainViewModel(saveTakenPhotoUseCase)
    }

    @Provides
    fun provideMainViewModelFactory (mainViewModel: MainViewModel): MainViewModelFactory {
        return MainViewModelFactory(mainViewModel)
    }

    @Provides
    fun provideGetAllPhotoUseCase (repository: RoomRepository) : GetAllPhotoUseCase {
        return GetAllPhotoUseCase(repository)
    }

    @Provides
    fun provideFirstViewModel (getAllPhotoUseCase: GetAllPhotoUseCase): FirstViewModel {
        return  FirstViewModel(getAllPhotoUseCase)
    }

    @Provides
    fun provideFirstViewModelFactory (firstViewModel: FirstViewModel):FirstViewModelFactory {
        return FirstViewModelFactory(firstViewModel)
    }
}