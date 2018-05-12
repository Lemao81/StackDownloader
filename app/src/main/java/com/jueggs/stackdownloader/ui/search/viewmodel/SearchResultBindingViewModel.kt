package com.jueggs.stackdownloader.ui.search.viewmodel

import android.databinding.BaseObservable

class SearchResultBindingViewModel(private val viewModel: SearchViewModel) : BaseObservable() {
    fun onDownload() = viewModel.onDownload()
}