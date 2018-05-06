package com.jueggs.stackdownloader

import com.jueggs.andutils.base.BaseApplication

class App : BaseApplication() {
    override fun koinModules() = listOf(appModule)
}