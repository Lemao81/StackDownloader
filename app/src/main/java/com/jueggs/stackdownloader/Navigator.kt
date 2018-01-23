package com.jueggs.stackdownloader

import com.jueggs.stackdownloader.activity.MainActivity
import com.jueggs.stackdownloader.activity.SearchActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class Navigator @Inject constructor(private val app: App) {
    fun navigateFromMainToSearchActivity(mainActivity: MainActivity) {
        mainActivity.startActivity(app.intentFor<SearchActivity>().newTask().clearTask())
    }
}