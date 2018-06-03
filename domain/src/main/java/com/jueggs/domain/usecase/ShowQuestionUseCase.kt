package com.jueggs.domain.usecase

import com.jueggs.domain.Repository
import com.jueggs.domain.model.*
import kotlinx.coroutines.experimental.async
import sun.plugin.dom.exception.InvalidStateException

class ShowQuestionUseCase(private val repository: Repository) : UseCase<ShowQuestionRequest, ShowQuestionResult> {

    override fun execute(req: ShowQuestionRequest?): ShowQuestionResult {
        val request = req ?: throw InvalidStateException("ShowQuestionRequest must not be null")

        val deferred = async {
            val answers = repository.getAnswersOfQuestionIncludingOwner(request.question.id)
            AnswerResult(answers)
        }

        return ShowQuestionResult(deferred)
    }
}