package com.jueggs.stackdownloader

import com.jueggs.stackdownloader.dagger.*
import com.jueggs.utils.base.BaseApplication

class App : BaseApplication() {
    override fun initialize() {
        applicationComponent = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
//        presenterComponent = DaggerPresenterComponent.builder().presenterModule(PresenterModule()).build()
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
//        lateinit var presenterComponent: PresenterComponent
    }
}