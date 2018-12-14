package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question

sealed class UseCaseResult

object Loading : UseCaseResult()

object Complete : UseCaseResult()

object NoNetwork : UseCaseResult()

data class Error(val throwable: Throwable) : UseCaseResult()

object EmptyInput : UseCaseResult()

object TagAlreadyAdded : UseCaseResult()

object TagNotAvailable : UseCaseResult()

data class TagAdded(val tags: MutableList<String>) : UseCaseResult()

object NoDataDownloaded : UseCaseResult()

data class Answers(val question: Question, val answers: List<Answer>) : UseCaseResult()