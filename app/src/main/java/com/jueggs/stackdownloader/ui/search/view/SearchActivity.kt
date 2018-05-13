package com.jueggs.stackdownloader.ui.search.view

import android.arch.lifecycle.Observer
import android.view.View
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.andutils.pairOf
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.architecture.ext.viewModel

class SearchActivity : BaseActivity(), SearchCriteriaFragment.Listener, SearchResultFragment.Listener {
    val viewModel by viewModel<SearchViewModel>()

    override fun layout() = R.layout.activity_search
    override fun toolbar(): View? = toolbar
    override fun toolbarNavigateBack() = false

    override fun singlePaneFragment() = pairOf(R.id.fragment, SearchCriteriaFragment.newInstance())
    override fun twoPaneFragments() = pairOf(
            pairOf(R.id.fragment1, SearchCriteriaFragment.newInstance()),
            pairOf(R.id.fragment2, SearchResultFragment.newInstance()))

    override fun setListeners() {
        if (AppMode.twoPane)
            viewModel.showHomeButton.observe(this, Observer { supportActionBar?.setDisplayHomeAsUpEnabled(it ?: false) })
    }

    override fun onStartSearch() {
        if (AppMode.singlePane)
            replaceFragment(R.id.fragment, SearchResultFragment.newInstance())
    }
}