package com.jueggs.stackdownloader.ui.search.view

import android.view.View
import com.jueggs.andutils.*
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.andutils.util.AppMode
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(), SearchCriteriaFragment.Listener, SearchResultFragment.Listener {
    override fun layout() = R.layout.activity_search
    override fun toolbar(): View? = toolbar
    override fun toolbarNavigateBack() = false

    override fun singlePaneFragment() = pairOf(R.id.fragmentContainer, SearchCriteriaFragment.newInstance())
    override fun twoPaneFragments() = pairOf(
            pairOf(R.id.fragmentContainer1, SearchCriteriaFragment.newInstance()),
            pairOf(R.id.fragmentContainer2, SearchResultFragment.newInstance()))

    override fun onStartSearch(searchCriteria: SearchCriteria) {
        if (AppMode.singlePane) {
            replaceFragment(R.id.fragmentContainer, SearchResultFragment.newInstance())
        } else {

        }
    }

    override fun showToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}