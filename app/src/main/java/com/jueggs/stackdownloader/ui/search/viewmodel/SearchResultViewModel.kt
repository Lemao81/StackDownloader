package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.ui.search.usecase.ShowQuestionUseCase

class SearchResultViewModel(
        private val
        private val showQuestionUseCase: ShowQuestionUseCase,
        ) {
    var questions: MutableLiveData<List<Question>> = MutableLiveData()
    val answers: MutableLiveData<Pair<Question, List<Answer>>> = MutableLiveData()

    fun onShowQuestion(question: Question) = showQuestionUseCase.go(this, question)
}