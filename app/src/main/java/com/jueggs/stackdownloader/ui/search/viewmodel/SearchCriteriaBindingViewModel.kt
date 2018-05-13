package com.jueggs.stackdownloader.ui.search.viewmodel

import android.databinding.*
import com.jueggs.stackdownloader.BR
import org.joda.time.DateTime
import java.util.*

class SearchCriteriaBindingViewModel(private val viewModel: SearchViewModel) : BaseObservable() {
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

    @Bindable
    var from: Date = viewModel.from
        set(value) {
            field = value
            viewModel.from = value
            notifyPropertyChanged(BR.from)
        }

    @Bindable
    var to: Date = viewModel.to
        set(value) {
            field = value
            viewModel.to = value
            notifyPropertyChanged(BR.to)
        }

    fun onAddTag() = viewModel.onAddTag()

    fun onStartSearch() = viewModel.onStartSearch()

    fun onEditFromDate() = viewModel.onEditFromDate()

    fun onEditToDate() = viewModel.onEditToDate()

    fun onToday() {
        from = Date()
        to = Date()
    }

    fun onLastWeek() {
        from = DateTime().minusWeeks(1).toDate()
        to = Date()
    }

    fun onLastMonth() {
        from = DateTime().minusMonths(1).toDate()
        to = Date()
    }
}