package com.jueggs.stackdownloader.ui.search.view

import android.view.View
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.pairOf
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.*
import org.koin.android.architecture.ext.viewModel

class SearchActivity : BaseActivity(), SearchCriteriaFragment.Listener, SearchResultFragment.Listener {
    val viewModel by viewModel<SearchViewModel>()

    override fun layout() = R.layout.activity_search
    override fun toolbar(): View? = toolbar
    override fun toolbarNavigateBack() = false

    override fun singlePaneFragment() =
            if (viewModel.isDataDownloaded)
                pairOf(R.id.fragment, SearchResultFragment.newInstance())
            else
                pairOf(R.id.fragment, SearchCriteriaFragment.newInstance())

    override fun twoPaneFragments() = pairOf(
            pairOf(R.id.fragment1, SearchCriteriaFragment.newInstance()),
            pairOf(R.id.fragment2, SearchResultFragment.newInstance()))

    override fun onInitialStart() = viewModel.onInitialStart()

    @Suppress("PLUGIN_WARNING")
    override fun setListeners() {
        viewModel.onHideKeyboard.nonNull().observe(this) { hideKeyboard() }
        viewModel.onShowProgress.nonNull().observe(this) { show -> if (show) progress.visible() else progress.gone() }
        viewModel.errors.nonNull().observe(this) { longToast(it) }
        viewModel.toasts.nonNull().observe(this) { toast(it) }

        when {
            AppMode.singlePane -> {
                viewModel.onSearch.nonNull().observe(this) {
                    addFragment(R.id.fragment, SearchResultFragment.newInstance())
                    toggleHomeAsUp(true)
                }
                botNavigation.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.menuSearch -> {
                            replaceFragment(R.id.fragment, SearchCriteriaFragment.newInstance())
                            item.isEnabled = true
                            true
                        }
                        R.id.menuItems -> {
                            replaceFragment(R.id.fragment, SearchResultFragment.newInstance())
                            item.isEnabled = true
                            true
                        }
                        else -> false
                    }
                }
            }
            AppMode.twoPane -> viewModel.answers.nonNull().observe(this) { toggleHomeAsUp(true) }
        }
    }

    override fun onBackPressed() {
        if (AppMode.twoPane)
            toggleHomeAsUp(false)

        super.onBackPressed()
    }
}