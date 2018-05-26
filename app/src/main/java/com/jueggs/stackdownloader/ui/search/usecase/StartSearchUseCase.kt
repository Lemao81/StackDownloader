package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.data.*
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import com.jueggs.stackdownloader.util.postFire
import kotlinx.coroutines.experimental.async

class StartSearchUseCase(private val repository: Repository, private val dataProvider: DataProvider) {
    fun go(viewModel: SearchViewModel) {
        if (viewModel.app.isNetworkConnected()) {
            val searchCriteria = SearchCriteria(viewModel.orderType.value, viewModel.sortType.value, viewModel.selectedTags.value, viewModel.fromDate.get(), viewModel.toDate.get())
            async {
                repository.deleteDownloadedData()
                dataProvider.fetchQuestions(searchCriteria).subscribe { questions ->
                    repository.addQuestions(questions)
                    viewModel.questions.postValue(repository.getAllQuestionsIncludingOwnerAndTags())
                    viewModel.onSearch.postFire()
                }
            }
        } else
            viewModel.errors.value = R.string.error_no_network
    }
}