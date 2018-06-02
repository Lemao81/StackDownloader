package com.jueggs.domain.model

import io.reactivex.Single

sealed class UseCaseResult

object Success : UseCaseResult()

data class Failure(val exception: Throwable) : UseCaseResult()

data class DownloadResult(val deferredResult: Single<UseCaseResult>) : UseCaseResult()