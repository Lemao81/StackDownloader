package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.stackdownloader.ui.search.view.SearchActivity
import com.jueggs.stackdownloader.util.observeNonNull

class TwoPaneSearchActivityDelegate : AppModeDelegate<SearchActivity> {
    override fun onInitialStart(delegator: SearchActivity) {}

    override fun setListeners(activity: SearchActivity) {
        activity.apply {
            viewModel.getShowQuestionResult().observeNonNull(this) { activity.toggleHomeAsUp(true) }
        }
    }

    override fun onBackPressed(activity: SearchActivity) {
        activity.apply {
            toggleHomeAsUp(false)
        }
    }
}