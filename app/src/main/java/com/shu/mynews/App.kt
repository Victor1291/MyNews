package com.shu.mynews

import android.app.Application
import com.shu.mynews.di.AppComponent
import com.shu.mynews.di.DaggerAppComponent

class App: Application() {


    companion object {
        lateinit var appComponentUser: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
       appComponentUser = DaggerAppComponent.builder().application(this).build()
    }

}