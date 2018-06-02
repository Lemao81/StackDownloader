package com.jueggs.domain.usecase

import com.jueggs.domain.model.*

interface UseCase<in TRequest : UseCaseRequest, out TResponse : UseCaseResult> {
    fun execute(req: TRequest? = null): TResponse
}