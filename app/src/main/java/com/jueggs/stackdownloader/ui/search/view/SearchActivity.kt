package com.jueggs.stackdownloader.ui.search.view

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import com.jueggs.andutils.base.BaseActivity
import com.jueggs.andutils.extension.datePicker
import com.jueggs.andutils.extension.getOr
import com.jueggs.andutils.extension.gone
import com.jueggs.andutils.extension.nonNull
import com.jueggs.andutils.extension.observe
import com.jueggs.andutils.extension.showConfirmDialog
import com.jueggs.andutils.extension.visible
import com.jueggs.andutils.util.AppMode
import com.jueggs.jutils.pairOf
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.ui.search.delegate.AppModeDelegate
import com.jueggs.stackdownloader.ui.search.usecase.Complete
import com.jueggs.stackdownloader.ui.search.usecase.Loading
import com.jueggs.stackdownloader.ui.search.usecase.NoNetwork
import com.jueggs.stackdownloader.ui.search.usecase.Error
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import com.jueggs.stackdownloader.util.isDebug
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.joda.time.LocalDate
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {
    val viewModel by viewModel<SearchViewModel>()
    val delegate: AppModeDelegate<SearchActivity> by inject(SearchActivity::class.simpleName ?: throw IllegalStateException())

    override fun layout() = R.layout.activity_search
    override fun bindingItems() = mapOf(BR.model to viewModel)
    override fun toolbar(): View? = toolbar
    override fun toolbarNavigateBack() = false
    override fun optionsMenu() = R.menu.toolbar

    override fun twoPaneFragments() = pairOf(
        pairOf(R.id.fragment1, SearchCriteriaFragment.newInstance()),
        pairOf(R.id.fragment2, SearchResultFragment.newInstance()))

    override fun initializeViews() {
        delegate.initializeViews(this)
    }

    override fun setListeners() {
        delegate.setListeners(this)

        viewModel.downloadDataResult.nonNull().observe(this) { result ->
            when (result) {
                NoNetwork -> longToast(R.string.error_no_network)
                Loading -> progress.visible()
                Complete -> {
                    progress.gone()
                    viewModel.isDataDownloaded = true
                    fabDownload.gone()
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

        viewModel.deleteDataResult.nonNull().observe(this) { result ->
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
        viewModel.questions.nonNull().observe(this) {
            viewModel.isQuestionsDownloaded = it.any()
            invalidateOptionsMenu()
        }
    }

    override fun onInitialStart() {
        delegate.onInitialStart(this)
        viewModel.onInitialStart()
    }

//    override fun onBackPressed() {
//        delegate.onBackPressed(this)
//        super.onBackPressed()
//    }

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()

    fun onEditFromDate(view: View) = datePicker(viewModel.fromDate.getOr(LocalDate.now()), viewModel.fromDate::set)

    fun onEditToDate(view: View) = datePicker(viewModel.toDate.getOr(LocalDate.now()), viewModel.toDate::set)

    fun onDeleteData(item: MenuItem) = showConfirmDialog(R.string.dialog_title_delete_data, R.string.dialog_text_delete_data) { viewModel.onDeleteData() }

    fun onGoToSearchCriteria(view: View) = navController?.navigate(R.id.action_searchResultFragment_to_searchCriteriaFragment)

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.mnuClearData)?.isEnabled = viewModel.isDataDownloaded || viewModel.isQuestionsDownloaded
        return super.onPrepareOptionsMenu(menu)
    }
}