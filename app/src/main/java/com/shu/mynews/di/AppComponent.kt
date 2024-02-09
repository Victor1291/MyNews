package com.shu.mynews.di

import android.app.Application
import com.shu.data.di.DataModule
import com.shu.mynews.ui.mainFragment.MainViewModelFactory
import dagger.BindsInstance
import dagger.Component


@Component (
    modules = [
        DataModule::class,
        DomainModule::class
    ]
        )
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build (): AppComponent

        @BindsInstance
        fun application (application: Application): Builder
    }

    fun mainViewModelFactory (): MainViewModelFactory

}