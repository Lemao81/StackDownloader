package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import com.jueggs.data.repository.LiveRepository
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.ui.search.usecase.*

class SearchResultViewModel(
        liveRepository: LiveRepository,
        private val showQuestionUseCase: ShowQuestionUseCase
) {
    val questions: LiveData<List<Question>> = liveRepository.getAllQuestionsIncludingOwnerAndTags()

    private val showQuestionInput: MutableLiveData<Question> = MutableLiveData()
    val showQuestionResult: LiveData<UseCaseResult> = Transformations.switchMap(showQuestionInput) { showQuestionUseCase.execute(ShowQuestionRequest(it)) }

    fun onShowQuestion(question: Question) = showQuestionInput.postValue(question)
}