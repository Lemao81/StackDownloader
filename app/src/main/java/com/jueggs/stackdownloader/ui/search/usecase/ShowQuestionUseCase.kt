package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.domain.Repository
import com.jueggs.domain.model.Question
import kotlinx.coroutines.experimental.launch

class ShowQuestionUseCase(private val repository: Repository) : UseCase<ShowQuestionRequest>() {

    override fun doExecute(request: ShowQuestionRequest) {
        launch {
            try {
                val answers = repository.getAnswersOfQuestionIncludingOwner(request.question.id)
                data.postValue(Answers(request.question, answers))
            } catch (exception: Exception) {
                data.postValue(Error(exception))
            }
        }
    }
}

data class ShowQuestionRequest(val question: Question)