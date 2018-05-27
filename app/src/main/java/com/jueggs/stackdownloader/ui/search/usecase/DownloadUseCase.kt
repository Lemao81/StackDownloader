package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.data.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.coroutines.experimental.async

class DownloadUseCase(private val repository: Repository, private val dataProvider: DataProvider) {
    fun go(viewModel: SearchViewModel) {
        if (viewModel.app.isNetworkConnected()) {
            async {
                val questionIds = viewModel.questions.value?.map { it.id }
                if (questionIds != null) {
                    dataProvider.fetchAnswers(questionIds).subscribe { answers ->
                        repository.addAnswers(answers)
                        viewModel.isDataDownloaded = true
                    }
                }
            }
        } else
            viewModel.errors.value = R.string.error_no_network
    }
}