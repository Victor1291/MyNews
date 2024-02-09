package com.shu.mynews.di

import com.shu.data.roomDb.RoomRepositoryImpl
import com.shu.domain.SaveTakenPhotoUseCase
import com.shu.mynews.ui.mainFragment.MainViewModel
import com.shu.mynews.ui.mainFragment.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSaveTakenPhotoUseCase (
        repository: RoomRepositoryImpl
    ) : SaveTakenPhotoUseCase {
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
}