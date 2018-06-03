package com.jueggs.stackdownloader.ui.search.view

import android.view.View
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.pairOf
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.delegate.AppModeDelegate
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity(), SearchCriteriaFragment.Listener, SearchResultFragment.Listener {
    val viewModel by viewModel<SearchViewModel>()
    val delegate: AppModeDelegate<SearchActivity> by inject()

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

    override fun onInitialStart() {
        viewModel.onInitialStart()
    }

    @Suppress("PLUGIN_WARNING")
    override fun setListeners() {
        viewModel.apply {
            onHideKeyboard.nonNull().observe(this@SearchActivity) { hideKeyboard() }
            onShowProgress.nonNull().observe(this@SearchActivity) { show -> if (show) progress.visible() else progress.gone() }
            onToast.nonNull().observe(this@SearchActivity) { toast(it) }
            onLongToast.nonNull().observe(this@SearchActivity) { longToast(it) }
            resultViewModel.questions.nonNull().observe(this@SearchActivity) { progress.gone() }
        }

        delegate.setListeners(this)
    }

    override fun onBackPressed() {
        delegate.onBackPressed(this)
        super.onBackPressed()
    }
}