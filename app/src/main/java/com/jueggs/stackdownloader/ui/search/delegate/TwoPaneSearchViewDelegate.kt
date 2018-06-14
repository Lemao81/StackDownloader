package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.andutils.extension.*
import com.jueggs.stackdownloader.ui.search.view.SearchActivity

class TwoPaneSearchViewDelegate : AppModeDelegate<SearchActivity> {
    override fun onInitialStart(delegator: SearchActivity) {}

    override fun setListeners(activity: SearchActivity) {
        activity.apply {
            viewModel.resultViewModel.showQuestionResult.nonNull().observe(this) { activity.toggleHomeAsUp(true) }
        }
    }

    override fun onBackPressed(activity: SearchActivity) {
        activity.apply {
            toggleHomeAsUp(false)
        }
    }
}