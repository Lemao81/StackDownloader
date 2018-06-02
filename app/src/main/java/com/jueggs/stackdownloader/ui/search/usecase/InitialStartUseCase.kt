package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.domain.*
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import kotlinx.coroutines.experimental.async
import org.joda.time.DateTime
import java.util.*

class InitialStartUseCase(private val repository: Repository, private val dataProvider: DataProvider) {
    fun go(viewModel: SearchViewModel) {
        if (viewModel.isDataDownloaded) {
            async { viewModel.searchResultViewModel.questions.postValue(repository.getAllQuestionsIncludingOwnerAndTags()) }
        } else {
            if (viewModel.getApplication<App>().isNetworkConnected()) {
                async { dataProvider.fetchTags().subscribe { tags -> repository.addTags(tags) } }
            }

            //TODO development
            viewModel.searchCriteriaViewModel.tag.value = "java"
            viewModel.searchCriteriaViewModel.fromDate.set(DateTime().minusWeeks(1).toDate())
            viewModel.searchCriteriaViewModel.toDate.set(Date())
        }
    }
}