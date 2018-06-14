package com.jueggs.domain.model

import kotlinx.coroutines.experimental.Deferred

sealed class UseCaseResult

object Success : UseCaseResult()

data class Failure(val exception: Throwable) : UseCaseResult()


data class InitialStartResult(val deferredResult: Deferred<UseCaseResult>) : UseCaseResult()



data class ShowQuestionResult(val deferredResult: Deferred<UseCaseResult>) : UseCaseResult()

data class AnswerResult(val answers: List<Answer>) : UseCaseResult()