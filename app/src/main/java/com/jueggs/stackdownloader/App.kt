package com.jueggs.stackdownloader

import android.app.Application
import com.jueggs.stackdownloader.dagger.AppModule
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.stackdownloader.dagger.DaggerApplicationComponent
import com.jueggs.stackdownloader.utils.logUnhandledException

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, throwable -> logUnhandledException(throwable) }
        applicationComponent = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
    }
}