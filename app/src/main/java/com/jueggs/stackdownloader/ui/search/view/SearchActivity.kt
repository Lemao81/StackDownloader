package com.jueggs.stackdownloader.ui.search.view

import android.view.*
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
    override fun optionsMenu() = R.menu.toolbar

    override fun twoPaneFragments() = pairOf(
            pairOf(R.id.fragment1, SearchCriteriaFragment.newInstance()),
            pairOf(R.id.fragment2, SearchResultFragment.newInstance()))

    override fun setListeners() {
        delegate.setListeners(this)

        viewModel.getDownloadDataResult().observeNonNull(this) { result ->
            when (result) {
                NoNetwork -> longToast(R.string.error_no_network)
                Loading -> progress.visible()
                Complete -> {
                    progress.gone()
                    viewModel.isDataDownloaded = true
                    invalidateOptionsMenu()
                    toast(R.string.toast_data_downloaded)
                }
                is Error -> {
                    progress.gone()
                    longToast(R.string.error_download_failed)
                    if (AppMode.isDebug) throw result.throwable
                }
            }
        }
        viewModel.getDeleteDataResult().observeNonNull(this) { result ->
            when (result) {
                Complete -> {
                    viewModel.isDataDownloaded = false
                    invalidateOptionsMenu()
                    toast(R.string.toast_data_deleted)
                }
                is Error -> {
                    longToast(R.string.error_something_wrong)
                    if (AppMode.isDebug) throw result.throwable
                }
            }
        }
        viewModel.questions.observeNonNull(this) {
            viewModel.isQuestionsDownloaded = it.any()
            invalidateOptionsMenu()
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

    fun onEditFromDate(view: View) = datePicker(viewModel.fromDate.getOr(Date()), viewModel.fromDate::set)

    fun onEditToDate(view: View) = datePicker(viewModel.toDate.getOr(Date()), viewModel.toDate::set)

    //TODO lib
    fun onDeleteData(item: MenuItem) {
        showConfirmDialog(R.string.dialog_title_delete_data, R.string.dialog_text_delete_data, { viewModel.onDeleteData() })
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.mnuClearData)?.isEnabled = viewModel.isDataDownloaded || viewModel.isQuestionsDownloaded
        return super.onPrepareOptionsMenu(menu)
    }
}