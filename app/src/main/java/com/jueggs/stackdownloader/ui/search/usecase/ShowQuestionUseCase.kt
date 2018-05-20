package com.jueggs.stackdownloader.ui.search.usecase

import android.arch.lifecycle.Transformations
import com.jueggs.andutils.pairOf
import com.jueggs.data.Repository
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.ui.search.SearchViewModel

class ShowQuestionUseCase(private val repository: Repository) {
    fun go(viewModel: SearchViewModel, question: Question) {
        viewModel.answers.value = Transformations.map(repository.getAnswersOfQuestion(question.id), { answers -> pairOf(question, answers) })
    }
}