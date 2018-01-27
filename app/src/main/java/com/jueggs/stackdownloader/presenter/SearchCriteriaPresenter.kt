package com.jueggs.stackdownloader.presenter

import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.model.SearchCriteria
import com.jueggs.stackdownloader.presenter.interfaces.ISearchCriteriaPresenter
import com.jueggs.stackdownloader.util.checkCast
import com.jueggs.stackdownloader.view.SearchCriteriaView
import com.jueggs.stackdownloader.view.SearchCriteriaViewModel
import com.jueggs.stackdownloader.view.SearchCriteriaViewStub
import com.jueggs.stackdownloader.view.SearchView
import com.jueggs.utils.base.BasePresenter
import com.jueggs.utils.extension.isNetworkConnected
import javax.inject.Inject

class SearchCriteriaPresenter : BasePresenter<SearchCriteriaView, SearchCriteriaViewModel>(), ISearchCriteriaPresenter {
    @Inject
    lateinit var app: App

    init {
        App.applicationComponent.inject(this)
    }

    override fun onStartSearch() {
        if (app.isNetworkConnected()) {
            if (activity != null) {
                checkCast<SearchView>(activity!!)
                val searchCriteria = SearchCriteria(
                        view.getPageSize(),
                        view.getSortOrder(),
                        view.getOrderType(),
                        view.getMinScore(),
                        view.getTags()
                )
                (activity as SearchView).onStartSearch(searchCriteria)
            }
        } else
            view.showLongToast(R.string.message_no_network)
    }

    override fun viewStub(): SearchCriteriaView = SearchCriteriaViewStub()
}
