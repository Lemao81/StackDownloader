package com.jueggs.stackdownloader

import android.app.Application
import com.jueggs.stackdownloader.dagger.AppModule
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.stackdownloader.dagger.DaggerApplicationComponent

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
    }
}