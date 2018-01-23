package com.jueggs.stackdownloader.presenter

import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.data.DataProvider
import com.jueggs.stackdownloader.model.SearchCriteria
import com.jueggs.stackdownloader.util.mapNullSafe
import com.jueggs.stackdownloader.view.SearchCriteriaView
import com.jueggs.utils.base.BaseFragmentPresenter
import com.jueggs.utils.base.LifecycleOwnerStub
import com.jueggs.utils.extension.isNetworkConnected

class SearchCriteriaPresenter(private val app: App, private val dataProvider: DataProvider, private val searchResultPresenter: SearchResultPresenter) : BaseFragmentPresenter<SearchCriteriaView>() {
    fun onStartSearch() {
        if (app.isNetworkConnected()) {
            val searchCriteria = SearchCriteria(
                    view.getPageSize(),
                    view.getSortOrder(),
                    view.getOrderType(),
                    view.getMinScore(),
                    view.getTags()
            )

            dataProvider.provideQuestionData(searchCriteria,
                    { itemShellData ->
                        val questions = itemShellData.mapNullSafe().items.map { it.mapNullSafe() }
                        searchResultPresenter.presentQuestions(questions)
                    },
                    {
                        view.longToast(it)
                    })
        } else
            view.longToast(R.string.message_no_network)
    }

    override fun viewStub(): SearchCriteriaView = object : SearchCriteriaView, LifecycleOwnerStub {}
}
