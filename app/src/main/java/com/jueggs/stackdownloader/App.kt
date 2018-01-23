package com.jueggs.stackdownloader

import com.jueggs.stackdownloader.dagger.AppModule
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.stackdownloader.dagger.DaggerApplicationComponent
import com.jueggs.utils.base.BaseApplication
import javax.inject.Inject

class App : BaseApplication() {
    @Inject
    lateinit var applicationComponent: ApplicationComponent

    override fun initialize() {
        applicationComponent = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
    }
}