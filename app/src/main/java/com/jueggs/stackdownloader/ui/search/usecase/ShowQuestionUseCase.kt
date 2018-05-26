package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.pairOf
import com.jueggs.data.Repository
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.coroutines.experimental.async

class ShowQuestionUseCase(private val repository: Repository) {
    fun go(viewModel: SearchViewModel, question: Question) {
        async { viewModel.answers.postValue(pairOf(question, repository.getAnswersOfQuestion(question.id))) }
    }
}