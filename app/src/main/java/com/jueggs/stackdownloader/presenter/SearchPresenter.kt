package com.jueggs.stackdownloader.presenter

import com.jueggs.stackdownloader.presenter.interfaces.ISearchPresenter
import com.jueggs.stackdownloader.view.SearchView
import com.jueggs.stackdownloader.view.SearchViewModel
import com.jueggs.stackdownloader.view.SearchViewStub
import com.jueggs.utils.base.BasePresenter

class SearchPresenter : BasePresenter<SearchView, SearchViewModel>(), ISearchPresenter {
    override fun viewStub(): SearchView = SearchViewStub()
}
