package com.jueggs.stackdownloader.ui.search.view

import android.view.View
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.pairOf
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.SearchViewModel
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
        if (AppMode.singlePane)
            viewModel.onSearch.nonNull().observe(this) { replaceFragment(R.id.fragment, SearchResultFragment.newInstance()) }
        if (AppMode.twoPane)
            viewModel.answers.nonNull().observe(this) { supportActionBar?.setDisplayHomeAsUpEnabled(true) }
    }

    override fun onMenuItemSelected(id: Int) =
            when (id) {
                android.R.id.home -> {
                    if (AppMode.twoPane) {
                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                        true
                    } else false
                }
                else -> null
            }
}