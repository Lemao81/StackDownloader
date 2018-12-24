package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.andutils.extension.nonNull
import com.jueggs.andutils.extension.observe
import com.jueggs.stackdownloader.ui.search.view.SearchActivity

class TwoPaneSearchActivityDelegate : AppModeDelegate<SearchActivity>() {

    override fun setListenersInternal(): SearchActivity.() -> Unit = {
        viewModel.showQuestionResult.nonNull().observe(this) { this.toggleHomeAsUp(true) }
    }

    override fun onBackPressedInternal(): SearchActivity.() -> Unit = {
        //        activity.apply {
//            toggleHomeAsUp(false)
//        }
    }
}