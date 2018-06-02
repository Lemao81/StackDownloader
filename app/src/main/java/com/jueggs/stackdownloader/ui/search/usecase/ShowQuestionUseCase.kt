package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.pairOf
import com.jueggs.domain.Repository
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.viewmodel.*
import kotlinx.coroutines.experimental.async

class ShowQuestionUseCase(private val repository: Repository) {
    fun go(viewModel: SearchResultViewModel, question: Question) {
        if (viewModel.isDataDownloaded)
            async { viewModel.answers.postValue(pairOf(question, repository.getAnswersOfQuestionIncludingOwner(question.id))) }
        else
            viewModel.onError.value = R.string.error_no_data_downloaded
    }
}