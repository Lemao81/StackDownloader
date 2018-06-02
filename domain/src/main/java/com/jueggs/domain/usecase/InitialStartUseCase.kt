package com.jueggs.domain.usecase

import com.jueggs.domain.*
import com.jueggs.domain.model.*
import io.reactivex.Single
import kotlinx.coroutines.experimental.launch

class InitialStartUseCase(private val repository: Repository, private val dataProvider: DataProvider) : UseCase<UseCaseRequest, InitialStartResult> {
    override fun execute(req: UseCaseRequest?): InitialStartResult {
        return InitialStartResult(Single.create { emitter ->
            launch {
                try {
                    dataProvider.fetchTags().subscribe { tags ->
                        try {
                            repository.addTags(tags)
                            emitter.onSuccess(Success)
                        } catch (exception: Exception) {
                            emitter.onSuccess(Failure(exception))
                        }
                    }
                } catch (exception: Exception) {
                    emitter.onSuccess(Failure(exception))
                }
            }
        })
    }
}