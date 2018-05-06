package com.jueggs.stackdownloader.ui

import com.jueggs.andutils.base.BaseMainActivity
import com.jueggs.andutils.postDelayed
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.view.SearchActivity
import org.jetbrains.anko.*

class MainActivity : BaseMainActivity() {

    override fun layout() = R.layout.activity_main

    override fun onResume() {
        super.onResume()
        postDelayed(2000) { startActivity(intentFor<SearchActivity>().newTask().clearTask()) }
    }
}