package com.jueggs.domain.usecase

import com.jueggs.domain.*
import com.jueggs.domain.model.*
import kotlinx.coroutines.experimental.async

class InitialStartUseCase(private val repository: Repository, private val dataProvider: DataProvider) : UseCase<UseCaseRequest, InitialStartResult> {

    override fun execute(req: UseCaseRequest?): InitialStartResult {
        val deferred = async {
            try {
                val tags = dataProvider.fetchTags().blockingGet()
                repository.addTags(tags)
                Success
            } catch (exception: Exception) {
                Failure(exception)
            }
        }

        return InitialStartResult(deferred)
    }
}