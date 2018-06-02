package com.jueggs.domain.usecase

import com.jueggs.domain.*
import com.jueggs.domain.model.*
import io.reactivex.Single
import kotlinx.coroutines.experimental.launch

class DownloadUseCase(private val repository: Repository, private val dataProvider: DataProvider) : UseCase<UseCaseRequest, DownloadResult> {
    override fun execute(request: UseCaseRequest?): DownloadResult {
        return DownloadResult(Single.create { emitter ->
            launch {
                try {
                    val questionIds = repository.getAllQuestionIds()
                    dataProvider.fetchAnswers(questionIds).subscribe({
                        try {
                            repository.addAnswers(it)
                            emitter.onSuccess(Success)
                        } catch (exception: Exception) {
                            emitter.onSuccess(Failure(exception))
                        }
                    }, {
                        emitter.onSuccess(Failure(it))
                    })
                } catch (exception: Exception) {
                    emitter.onSuccess(Failure(exception))
                }
            }
        })
    }
}