package com.jueggs.stackdownloader.ui.search.viewmodel

import android.databinding.BaseObservable

class SearchResultBindingViewModel(private val viewModel: SearchResultViewModel) : BaseObservable() {
    fun onDownload() = viewModel.onDownload()
}