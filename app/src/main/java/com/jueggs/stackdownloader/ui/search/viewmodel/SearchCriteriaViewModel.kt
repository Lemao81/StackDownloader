package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.AndroidViewModel
import com.jueggs.andutils.checkCast
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.bo.SearchCriteria
import com.jueggs.stackdownloader.view.SearchView

class SearchCriteriaViewModel(app: App) : AndroidViewModel(app) {
    fun onStartSearch() {
        if (getApplication<App>().isNetworkConnected()) {
                val searchCriteria = SearchCriteria(
                        view.getPageSize(),
                        view.getSortOrder(),
                        view.getOrderType(),
                        view.getMinScore(),
                        view.getTags()
                )
                (activity as SearchView).onStartSearch(searchCriteria)
        } else
            view.showLongToast(R.string.toast_no_network)
    }
}