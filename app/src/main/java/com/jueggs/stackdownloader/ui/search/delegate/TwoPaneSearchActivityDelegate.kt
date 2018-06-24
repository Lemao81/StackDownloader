package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.stackdownloader.ui.search.view.SearchActivity
import com.jueggs.stackdownloader.util.observeNonNull

class TwoPaneSearchActivityDelegate : AppModeDelegate<SearchActivity>() {

    override fun setListenersInternal(): SearchActivity.() -> Unit = {
        viewModel.getShowQuestionResult().observeNonNull(this) { this.toggleHomeAsUp(true) }
    }

    override fun onBackPressedInternal(): SearchActivity.() -> Unit = {
        //        activity.apply {
//            toggleHomeAsUp(false)
//        }
    }
}