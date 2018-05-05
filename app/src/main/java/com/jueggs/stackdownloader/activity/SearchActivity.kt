package com.jueggs.stackdownloader.activity

import android.view.View
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.bo.SearchCriteria
import com.jueggs.stackdownloader.presenter.interfaces.ISearchPresenter
import com.jueggs.stackdownloader.view.*
import com.jueggs.utils.base.*
import com.jueggs.utils.checkCast
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity<SearchView, SearchViewModel>(), SearchView {
    @Inject
    lateinit var presenter: ISearchPresenter

    override fun layout() = R.layout.activity_search
    override fun inject() = App.applicationComponent.inject(this)
    override fun presenter() = presenter as BasePresenter<SearchView, SearchViewModel>
    override fun self(): SearchView = this
    override fun toolbar(): View? = toolbar
    override fun shallToolbarNavigateBack() = false

    override fun viewModel() = SearchViewModel.EMPTY

    override fun onStartSearch(searchCriteria: SearchCriteria) {
        val searchResultFragment = supportFragmentManager.findFragmentById(R.id.fragSearchResult)
        checkCast<SearchResultView>(searchResultFragment)
        (searchResultFragment as SearchResultView).onStartSearch(searchCriteria)
    }

    override fun showToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}