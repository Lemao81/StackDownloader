package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.data.*
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.coroutines.experimental.async
import org.joda.time.DateTime
import java.util.*

class InitialStartUseCase(private val repository: Repository, private val dataProvider: DataProvider) {
    fun go(viewModel: SearchViewModel) {
        if (viewModel.isDataDownloaded) {
            async { viewModel.questions.postValue(repository.getAllQuestionsIncludingOwnerAndTags()) }
        } else {
            if (viewModel.app.isNetworkConnected()) {
                async { dataProvider.fetchTags().subscribe { tags -> repository.addTags(tags) } }
            }

            //TODO development
            viewModel.tag.value = "java"
            viewModel.fromDate.set(DateTime().minusWeeks(1).toDate())
            viewModel.toDate.set(Date())
        }
    }
}