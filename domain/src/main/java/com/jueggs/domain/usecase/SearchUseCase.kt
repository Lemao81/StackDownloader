package com.jueggs.domain.usecase

import com.jueggs.domain.*
import com.jueggs.domain.model.*
import io.reactivex.Single
import kotlinx.coroutines.experimental.async

class SearchUseCase(private val repository: Repository, private val dataProvider: DataProvider) : UseCase<SearchRequest, SearchResult> {
    override fun execute(req: SearchRequest?): SearchResult {
        val request = req ?: throw IllegalStateException("SearchRequest must not be null")

        return SearchResult(Single.create { emitter ->
            async {
                try {
                    repository.deleteDownloadedData()
                    dataProvider.fetchQuestions(request.criteria).subscribe { questions ->
                        try {
                            repository.addQuestions(questions)
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