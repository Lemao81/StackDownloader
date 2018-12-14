package com.jueggs.stackdownloader

import com.jueggs.andutils.base.BaseApplication
import org.koin.standalone.StandAloneContext.loadKoinModules

class App : BaseApplication(BuildConfig.DEBUG) {
    override fun initialize() {
        loadKoinModules(listOf(appModule))
    }
}