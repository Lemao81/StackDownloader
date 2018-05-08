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

    override fun singlePaneFragment() = pairOf(R.id.fragment, SearchCriteriaFragment.newInstance())
    override fun twoPaneFragments() = pairOf(
            pairOf(R.id.fragment1, SearchCriteriaFragment.newInstance()),
            pairOf(R.id.fragment2, SearchResultFragment.newInstance()))

    override fun onStartSearch(searchCriteria: SearchCriteria) {
        if (AppMode.singlePane) {
            val fragment = SearchResultFragment.newInstance(searchCriteria)
            replaceFragment(R.id.fragment, fragment)
            fragment.viewModel.startSearch()
        } else {
            findFragment<SearchResultFragment>(R.id.fragment2).apply {
                viewModel.searchCriteria = searchCriteria
                viewModel.startSearch()
            }
        }
    }

    override fun showToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}