package com.jueggs.stackdownloader.ui

import com.jueggs.andutils.Util.postDelayed
import com.jueggs.andutils.base.BaseMainActivity
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.view.SearchActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class MainActivity : BaseMainActivity() {

    override fun layout() = R.layout.activity_main

    override fun onResume() {
        super.onResume()
        postDelayed(2000) { startActivity(intentFor<SearchActivity>().newTask().clearTask()) }
    }
}