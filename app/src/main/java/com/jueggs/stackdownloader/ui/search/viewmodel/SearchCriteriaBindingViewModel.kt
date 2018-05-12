package com.jueggs.stackdownloader.ui.search.viewmodel

import android.databinding.*
import com.jueggs.stackdownloader.BR

class SearchCriteriaBindingViewModel(private val viewModel: SearchViewModel) : BaseObservable() {
    @Bindable
    var limitTo: String = viewModel.limitTo
        set(value) {
            field = value
            notifyPropertyChanged(BR.limitTo)
        }

    @Bindable
    var score: String = viewModel.score
        set(value) {
            field = value
            notifyPropertyChanged(BR.score)
        }

    @Bindable
    var tag: String = viewModel.tag
        set(value) {
            field = value
            notifyPropertyChanged(BR.tag)
        }

    @Bindable
    var orderType: Int = viewModel.orderType
        set(value) {
            field = value
            notifyPropertyChanged(BR.orderType)
        }

    @Bindable
    var sortType: Int = viewModel.sortType
        set(value) {
            field = value
            notifyPropertyChanged(BR.sortType)
        }

    fun onAddTag() = viewModel.onAddTag()

    fun onStartSearch() = viewModel.onStartSearch()
}