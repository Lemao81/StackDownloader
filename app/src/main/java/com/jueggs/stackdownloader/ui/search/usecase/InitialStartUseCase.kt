package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.data.*
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.coroutines.experimental.async

class InitialStartUseCase(private val repository: Repository, private val dataProvider: DataProvider) {
    fun go(viewModel: SearchViewModel) {
        if (viewModel.app.isNetworkConnected()) {
            async { dataProvider.fetchTags().subscribe { tags -> repository.addTags(tags) } }
        }
    }
}