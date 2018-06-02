package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import com.jueggs.andutils.extension.fireId
import com.jueggs.andutils.pairOf
import com.jueggs.data.repository.LiveRepository
import com.jueggs.domain.model.*
import com.jueggs.domain.usecase.ShowQuestionUseCase
import com.jueggs.stackdownloader.R

class SearchResultViewModel(
        liveRepository: LiveRepository,
        private val showQuestionUseCase: ShowQuestionUseCase
) {
    val onLongToast: MutableLiveData<Int> = MutableLiveData()
    val questions: LiveData<List<Question>> = liveRepository.getAllQuestionsIncludingOwnerAndTags()

    private val _answers: MutableLiveData<Pair<Question, List<Answer>>> = MutableLiveData()
    val answers: LiveData<Pair<Question, List<Answer>>>
        get() = _answers

    fun onShowQuestion(question: Question) {
        val result = showQuestionUseCase.execute(ShowQuestionRequest(question))

        if (result.answers.isEmpty())
            onLongToast.fireId(R.string.error_no_data_downloaded)
        else
            _answers.value = pairOf(question, result.answers)
    }
}