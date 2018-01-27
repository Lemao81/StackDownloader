package com.jueggs.stackdownloader.view

import android.arch.lifecycle.Lifecycle
import com.jueggs.stackdownloader.model.SearchCriteria
import com.jueggs.utils.base.BaseView
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

interface SearchView : BaseView {
    fun showToolbarHomeButton()
    fun hideToolbarHomeButton()
    fun onStartSearch(searchCriteria: SearchCriteria)
}

@PaperParcel
class SearchViewModel : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelSearchViewModel.CREATOR
        val EMPTY = SearchViewModel()
    }
}

class SearchViewStub : SearchView {
    override fun onStartSearch(searchCriteria: SearchCriteria) {
        TODO("not implemented")
    }

    override fun showToolbarHomeButton() {
        TODO("not implemented")
    }

    override fun hideToolbarHomeButton() {
        TODO("not implemented")
    }

    override fun getLifecycle(): Lifecycle {
        TODO("not implemented")
    }
}