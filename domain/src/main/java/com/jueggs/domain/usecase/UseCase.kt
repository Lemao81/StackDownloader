package com.jueggs.domain.usecase

import com.jueggs.domain.model.UseCaseRequest
import com.jueggs.domain.model.UseCaseResult

interface UseCase<in TRequest : UseCaseRequest, out TResponse : UseCaseResult> {
    fun execute(request: TRequest? = null): TResponse
}