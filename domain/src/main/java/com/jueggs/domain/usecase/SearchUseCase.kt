package com.jueggs.domain.usecase

import com.jueggs.domain.*
import com.jueggs.domain.model.*
import kotlinx.coroutines.experimental.async

class SearchUseCase(private val repository: Repository, private val dataProvider: DataProvider) : UseCase<SearchRequest, SearchResult> {

    override fun execute(req: SearchRequest?): SearchResult {
        val request = req ?: throw IllegalStateException("SearchRequest must not be null")

        val deferred = async {
            try {
                repository.deleteDownloadedData()
                val questions = dataProvider.fetchQuestions(request.criteria).blockingGet()
                repository.addQuestions(questions)
                Success
            } catch (exception: Exception) {
                Failure(exception)
            }
        }

        return SearchResult(deferred)
    }
}