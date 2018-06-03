package com.jueggs.domain.usecase

import com.jueggs.domain.*
import com.jueggs.domain.model.*
import kotlinx.coroutines.experimental.async

class DownloadUseCase(private val repository: Repository, private val dataProvider: DataProvider) : UseCase<UseCaseRequest, DownloadResult> {

    override fun execute(req: UseCaseRequest?): DownloadResult {
        val deferred = async {
            try {
                val questionIds = repository.getAllQuestionIds()
                val answers = dataProvider.fetchAnswers(questionIds).blockingGet()
                repository.addAnswers(answers)
                Success
            } catch (exception: Exception) {
                Failure(exception)
            }
        }

        return DownloadResult(deferred)
    }
}