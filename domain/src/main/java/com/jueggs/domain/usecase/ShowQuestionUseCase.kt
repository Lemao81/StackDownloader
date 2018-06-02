package com.jueggs.domain.usecase

import com.jueggs.domain.Repository
import com.jueggs.domain.model.*
import sun.plugin.dom.exception.InvalidStateException

class ShowQuestionUseCase(private val repository: Repository) : UseCase<ShowQuestionRequest, ShowQuestionResult> {
    override fun execute(req: ShowQuestionRequest?): ShowQuestionResult {
        val request = req ?: throw InvalidStateException("ShowQuestionRequest must not be null")

        return ShowQuestionResult(repository.getAnswersOfQuestionIncludingOwner(request.question.id))
    }
}