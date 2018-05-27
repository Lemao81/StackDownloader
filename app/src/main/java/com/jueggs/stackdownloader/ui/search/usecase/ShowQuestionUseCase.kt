package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.pairOf
import com.jueggs.data.Repository
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.coroutines.experimental.async

class ShowQuestionUseCase(private val repository: Repository) {
    fun go(viewModel: SearchViewModel, question: Question) {
        if (viewModel.isDataDownloaded)
            async { viewModel.answers.postValue(pairOf(question, repository.getAnswersOfQuestion(question.id))) }
        else
            viewModel.errors.value = R.string.error_no_data_downloaded
    }
}