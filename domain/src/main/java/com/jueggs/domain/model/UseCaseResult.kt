package com.jueggs.domain.model

import io.reactivex.Single

sealed class UseCaseResult

object Success : UseCaseResult()

data class Failure(val exception: Throwable) : UseCaseResult()

data class DownloadResult(val deferredResult: Single<UseCaseResult>) : UseCaseResult()

data class InitialStartResult(val deferredResult: Single<UseCaseResult>) : UseCaseResult()


sealed class AddTagResult : UseCaseResult()

object EmptyInput : AddTagResult()

object TagAlreadyAdded : AddTagResult()

object TagNotAvailable : AddTagResult()

data class TagAdded(val tags: MutableList<String>) : AddTagResult()

data class AddTagFailure(val exception: Exception) : AddTagResult()


data class ShowQuestionResult(val answers: List<Answer>) : UseCaseResult()


data class SearchResult(val deferredResult: Single<UseCaseResult>) : UseCaseResult()