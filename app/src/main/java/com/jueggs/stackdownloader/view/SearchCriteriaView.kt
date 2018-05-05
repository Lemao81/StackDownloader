package com.jueggs.stackdownloader.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.os.Parcelable
import com.jueggs.utils.base.BaseView
import kotlinx.android.parcel.Parcelize


interface SearchCriteriaView : BaseView {
    fun getPageSize(): String
    fun getSortOrder(): String
    fun getOrderType(): String
    fun getMinScore(): String
    fun getTags(): List<String>
}

@SuppressLint("ParcelCreator")
@Parcelize
class SearchCriteriaViewModel : Parcelable {

    companion object {
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