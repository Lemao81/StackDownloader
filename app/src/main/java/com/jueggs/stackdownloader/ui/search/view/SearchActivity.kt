package com.jueggs.stackdownloader.ui.search.view

import android.view.View
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.pairOf
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.delegate.AppModeDelegate
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject
import java.util.*

class SearchActivity : BaseActivity(), SearchCriteriaFragment.Listener, SearchResultFragment.Listener {
    val viewModel by viewModel<SearchViewModel>()
    val delegate: AppModeDelegate<SearchActivity> by inject()

    override fun layout() = R.layout.activity_search
    override fun bindingItems() = mapOf(BR.model to viewModel)
    override fun toolbar(): View? = toolbar
    override fun toolbarNavigateBack() = false

    override fun twoPaneFragments() = pairOf(
            pairOf(R.id.fragment1, SearchCriteriaFragment.newInstance()),
            pairOf(R.id.fragment2, SearchResultFragment.newInstance()))

    override fun setListeners() {
        delegate.setListeners(this)

        viewModel.getDownloadResult().observeNonNull(this@SearchActivity) { result ->
            when (result) {
                NoNetwork -> longToast(R.string.error_no_network)
                Loading -> progress.visible()
                Complete -> {
                    progress.gone()
                    viewModel.isDataDownloaded = true
                    toast(R.string.toast_data_downloaded)
                }
                is Error -> {
                    progress.gone()
                    longToast(R.string.error_download_failed)
                    if (AppMode.isDebug) throw result.throwable
                }
            }
        }
    }

    override fun onInitialStart() {
        delegate.onInitialStart(this)
        viewModel.onInitialStart()
    }

    override fun onBackPressed() {
        delegate.onBackPressed(this)
        super.onBackPressed()
    }

    fun onEditFromDate(view: View) {
        datePicker(viewModel.fromDate.getOr(Date()), viewModel.fromDate::set)
    }

    fun onEditToDate(view: View) {
        datePicker(viewModel.toDate.getOr(Date()), viewModel.toDate::set)
    }
}