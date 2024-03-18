package com.shu.mynews.di

import com.shu.domain.CreateMessageUseCase
import com.shu.domain.GetAllCollectionsUseCase
import com.shu.domain.GetAllMessageUseCase
import com.shu.domain.GetAllPhotoUseCase
import com.shu.domain.RoomRepository
import com.shu.domain.SaveTakenPhotoUseCase
import com.shu.domain.collection.usecase.UpdateMessageUseCase
import com.shu.domain.habits.GetAllHabitsUseCase
import com.shu.domain.habits.HabitsRepository
import com.shu.domain.news.GetAllNewsUseCase
import com.shu.domain.news.NewsListRepository
import com.shu.domain.collection.repository.CollectionRepository
import com.shu.domain.usecase.collections.AddCollectionUseCase
import com.shu.mynews.ui.change.ChangeViewModel
import com.shu.mynews.ui.change.ChangeViewModelFactory
import com.shu.mynews.ui.collections.ProfileViewModel
import com.shu.mynews.ui.collections.ProfileViewModelFactory
import com.shu.mynews.ui.gallery.FirstViewModel
import com.shu.mynews.ui.gallery.FirstViewModelFactory
import com.shu.mynews.ui.home.HomeViewModel
import com.shu.mynews.ui.home.HomeViewModelFactory
import com.shu.mynews.ui.news.NewsViewModel
import com.shu.mynews.ui.news.NewsViewModelFactory
import com.shu.mynews.ui.mainFragment.MainViewModel
import com.shu.mynews.ui.mainFragment.MainViewModelFactory
import com.shu.mynews.ui.message.MessageViewModel
import com.shu.mynews.ui.message.MessageViewModelFactory
import com.shu.mynews.ui.repository.HabitsMemoryCache
import com.shu.mynews.ui.visitor.main.VisitorViewModel
import com.shu.mynews.ui.visitor.main.VisitorViewModelFactory
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

    @Provides
    fun provideGetAllNewsUseCase (repository: NewsListRepository) : GetAllNewsUseCase {
        return GetAllNewsUseCase(repository)
    }

    @Provides
    fun provideNewsViewModel (getAllNewsUseCase: GetAllNewsUseCase): NewsViewModel {
        return  NewsViewModel(getAllNewsUseCase)
    }

    @Provides
    fun provideNewsViewModelFactory (newsViewModel: NewsViewModel): NewsViewModelFactory {
        return NewsViewModelFactory(newsViewModel)
    }

    @Provides
    fun provideGetAllHabitsUseCase (repository: HabitsRepository) : GetAllHabitsUseCase {
        return GetAllHabitsUseCase(repository)
    }

    @Provides
    fun provideHabitsViewModel (habitsMemoryCache: HabitsMemoryCache, getAllHabitsUseCase: GetAllHabitsUseCase): HomeViewModel {
        return  HomeViewModel(habitsMemoryCache, getAllHabitsUseCase)
    }

    @Provides
    fun provideHomeViewModelFactory (homeViewModel: HomeViewModel): HomeViewModelFactory {
        return HomeViewModelFactory(homeViewModel)
    }

    @Provides
    fun provideVisitorViewModel (getAllNewsUseCase: GetAllNewsUseCase): VisitorViewModel {
        return  VisitorViewModel(getAllNewsUseCase)
    }

    @Provides
    fun provideVisitorViewModelFactory (visitorViewModel: VisitorViewModel): VisitorViewModelFactory {
        return VisitorViewModelFactory(visitorViewModel)
    }


    /*   @Provides
       @Reusable
       fun provideHabitsDao(habitsDatabaseContract: HabitsDatabaseContract): HabitsDao {
           return habitsDatabaseContract.habitsDao()
       }*/

    @Provides
    fun provideUpdateMessageUseCase (repository: CollectionRepository) : UpdateMessageUseCase {
        return UpdateMessageUseCase(repository)
    }

    @Provides
    fun provideChangeViewModel (updateMessageUseCase: UpdateMessageUseCase): ChangeViewModel {
        return  ChangeViewModel(updateMessageUseCase)
    }

    @Provides
    fun provideChangeViewModelFactory (changeViewModel: ChangeViewModel): ChangeViewModelFactory {
        return ChangeViewModelFactory(changeViewModel)
    }


    @Provides
    fun provideGetAllCollectionsUseCase (repository: CollectionRepository) : GetAllCollectionsUseCase {
        return GetAllCollectionsUseCase(repository)
    }

    @Provides
    fun provideAddCollectionUseCase (repository: CollectionRepository) : AddCollectionUseCase {
        return AddCollectionUseCase(repository)
    }

    @Provides
    fun provideCreateMessageUseCase (repository: CollectionRepository) : CreateMessageUseCase {
        return CreateMessageUseCase(repository)
    }

    @Provides
    fun provideProfileViewModel (getAllCollectionsUseCase: GetAllCollectionsUseCase,
                                addCollectionUseCase: AddCollectionUseCase,
                                 createMessageUseCase: CreateMessageUseCase
    ): ProfileViewModel {
        return  ProfileViewModel(getAllCollectionsUseCase,addCollectionUseCase,createMessageUseCase)
    }

    @Provides
    fun provideProfileViewModelFactory (profileViewModel: ProfileViewModel): ProfileViewModelFactory {
        return ProfileViewModelFactory(profileViewModel)
    }

    @Provides
    fun provideGetAllMessageUseCase (repository: CollectionRepository) : GetAllMessageUseCase {
        return GetAllMessageUseCase(repository)
    }

    @Provides
    fun provideMessageViewModel (getAllMessageUseCase: GetAllMessageUseCase,
                                 createMessageUseCase: CreateMessageUseCase
    ): MessageViewModel {
        return  MessageViewModel(getAllMessageUseCase,createMessageUseCase)
    }

    @Provides
    fun provideMessageViewModelFactory (messageViewModel: MessageViewModel): MessageViewModelFactory {
        return MessageViewModelFactory(messageViewModel)
    }
}