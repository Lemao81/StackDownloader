package com.jueggs.stackdownloader.ui.search.view

import android.view.View
import com.jueggs.andutils.*
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.bo.SearchCriteria
import com.jueggs.stackdownloader.view.SearchResultView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    override fun layout() = R.layout.activity_search
    override fun toolbar(): View? = toolbar
    override fun shallToolbarNavigateBack() = false

    override fun singlePaneFragment() = pairOf(R.id.fragmentContainer1, SearchCriteriaFragment.newInstance())
    override fun twoPaneFragments() = pairOf(pairOf(R.id.fragmentContainer1, SearchCriteriaFragment.newInstance()),
            pairOf(R.id.fragmentContainer2, SearchResultFragment.newInstance()))

    override fun showToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}