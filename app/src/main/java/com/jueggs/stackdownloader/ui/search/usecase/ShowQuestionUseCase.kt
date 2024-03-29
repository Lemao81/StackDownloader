package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.domain.Repository
import com.jueggs.domain.model.Question
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShowQuestionUseCase(private val repository: Repository) : UseCase<ShowQuestionRequest>() {

    override fun doExecute(request: ShowQuestionRequest) {
        if (!request.isDataDownloaded)
            data.postValue(NoDataDownloaded)
        else {
            // TODO remove globalscope
            GlobalScope.launch {
                try {
                    val answers = repository.getAnswersOfQuestionIncludingOwner(request.question.id)
                    data.postValue(Answers(request.question, answers))
                } catch (exception: Exception) {
                    data.postValue(Error(exception))
                }
            }
        }
    }
}

data class ShowQuestionRequest(val question: Question, val isDataDownloaded: Boolean)