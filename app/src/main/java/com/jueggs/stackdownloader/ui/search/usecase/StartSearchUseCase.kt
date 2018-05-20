package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.data.*
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.coroutines.experimental.async

class StartSearchUseCase(private val repository: Repository, private val dataProvider: DataProvider) {
    fun go(viewModel: SearchViewModel) {
        val searchCriteria = SearchCriteria(viewModel.orderType.value, viewModel.sortType.value, viewModel.selectedTags.value, viewModel.fromDate.get(), viewModel.toDate.get())
        viewModel.onSearch.value = searchCriteria

        if (viewModel.getApplication<App>().isNetworkConnected()) {
            async { dataProvider.fetchQuestions(searchCriteria).subscribe { questions -> repository.addQuestions(questions) } }
        }
    }
}