package com.jueggs.stackdownloader.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.os.Parcelable
import com.jueggs.stackdownloader.bo.SearchCriteria
import com.jueggs.utils.base.BaseView
import kotlinx.android.parcel.Parcelize

interface SearchView : BaseView {
    fun showToolbarHomeButton()
    fun hideToolbarHomeButton()
    fun onStartSearch(searchCriteria: SearchCriteria)
}

@SuppressLint("ParcelCreator")
@Parcelize
class SearchViewModel : Parcelable {

    companion object {
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