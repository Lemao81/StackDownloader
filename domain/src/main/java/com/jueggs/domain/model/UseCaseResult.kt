package com.jueggs.domain.model

import kotlinx.coroutines.experimental.Deferred

sealed class UseCaseResult

object Success : UseCaseResult()

data class Failure(val exception: Throwable) : UseCaseResult()


data class DownloadResult(val deferredResult: Deferred<UseCaseResult>) : UseCaseResult()


data class InitialStartResult(val deferredResult: Deferred<UseCaseResult>) : UseCaseResult()


data class AddTagResult(val deferredResult: Deferred<AddTagSealedResult>) : UseCaseResult()

sealed class AddTagSealedResult : UseCaseResult()

object EmptyInput : AddTagSealedResult()

object TagAlreadyAdded : AddTagSealedResult()

object TagNotAvailable : AddTagSealedResult()

data class TagAdded(val tags: MutableList<String>) : AddTagSealedResult()

data class AddTagFailure(val exception: Exception) : AddTagSealedResult()


data class ShowQuestionResult(val deferredResult: Deferred<UseCaseResult>) : UseCaseResult()

data class AnswerResult(val answers: List<Answer>) : UseCaseResult()


data class SearchResult(val deferredResult: Deferred<UseCaseResult>) : UseCaseResult()