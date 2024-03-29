package com.shu.mynews.di

import android.app.Application
import com.shu.data.di.DataModule
import com.shu.mynews.ui.change.ChangeViewModelFactory
import com.shu.mynews.ui.collections.ProfileViewModelFactory
import com.shu.mynews.ui.gallery.FirstViewModelFactory
import com.shu.mynews.ui.home.HomeViewModelFactory
import com.shu.mynews.ui.mainFragment.MainViewModelFactory
import com.shu.mynews.ui.message.MessageViewModelFactory
import com.shu.mynews.ui.news.NewsViewModelFactory
import com.shu.mynews.ui.visitor.main.VisitorViewModelFactory
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        RepoModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun mainViewModelFactory(): MainViewModelFactory
    fun firstViewModelFactory(): FirstViewModelFactory
    fun newsViewModelFactory(): NewsViewModelFactory

    fun homeViewModelFactory(): HomeViewModelFactory
    fun visitorViewModelFactory(): VisitorViewModelFactory
    fun changeViewModelFactory(): ChangeViewModelFactory
    fun profileViewModelFactory(): ProfileViewModelFactory
    fun messageViewModelFactory(): MessageViewModelFactory


}