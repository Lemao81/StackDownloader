package com.jueggs.stackdownloader.view

import android.arch.lifecycle.Lifecycle
import com.jueggs.utils.base.BaseView
import paperparcel.PaperParcel
import paperparcel.PaperParcelable


interface SearchCriteriaView : BaseView {
    fun getPageSize(): String
    fun getSortOrder(): String
    fun getOrderType(): String
    fun getMinScore(): String
    fun getTags(): List<String>
}

@PaperParcel
class SearchCriteriaViewModel : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelSearchCriteriaViewModel.CREATOR
        val EMPTY = SearchCriteriaViewModel()
    }
}

class SearchCriteriaViewStub : SearchCriteriaView {
    override fun getPageSize(): String {
        TODO("not implemented")
    }

    override fun getSortOrder(): String {
        TODO("not implemented")
    }

    override fun getOrderType(): String {
        TODO("not implemented")
    }

    override fun getMinScore(): String {
        TODO("not implemented")
    }

    override fun getTags(): List<String> {
        TODO("not implemented")
    }

    override fun getLifecycle(): Lifecycle {
        TODO("not implemented")
    }
}