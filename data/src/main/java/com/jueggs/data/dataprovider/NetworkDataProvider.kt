package com.jueggs.data.dataprovider

import android.content.Context
import com.jueggs.data.SORT_POPULAR
import com.jueggs.data.mapper.bo
import com.jueggs.data.retrofit.StackOverflowApi
import com.jueggs.data.retrofit.dto.*
import com.jueggs.domain.DataProvider
import com.jueggs.domain.model.*
import com.jueggs.jutils.extension.join
import io.reactivex.Single
import kotlinx.coroutines.experimental.launch

class NetworkDataProvider(private val context: Context, private val apiImpl: StackOverflowApi) : DataProvider {

    override suspend fun fetchTags(): Single<List<Tag>> {
        val queryParams = QueryParameter().also {
            it.pageSize = 100
            it.sort = SORT_POPULAR
        }.asMap(false)
        val data = apiImpl.fetchTags(queryParams).await()
        val bos = data.items?.map { it.bo }

        return Single.just(bos ?: emptyList())
    }

    override fun fetchQuestions(searchCriteria: SearchCriteria): Single<List<Question>> {
        return Single.create { emitter ->
            launch {
                try {
                    val queryParams = searchCriteria.mapToQueryParameter(this@NetworkDataProvider.context).asMap()
                    val data = apiImpl.fetchQuestions(queryParams).await()
                    val bos = data.items?.map { it.bo } ?: throw NoDataException()

                    emitter.onSuccess(bos)
                } catch (exception: Exception) {
                    emitter.onError(exception)
                }
            }
        }
    }

    override fun fetchAnswers(questionIds: List<Long>): Single<List<Answer>> {
        return Single.create { emitter ->
            launch {
                try {
                    val idJoin = questionIds.join(";")
                    val queryParams = QueryParameter().asMap()
                    val data = apiImpl.fetchAnswersOfQuestions(idJoin, queryParams).await()
                    val bos = data.items?.map { it.bo } ?: throw NoDataException()

                    emitter.onSuccess(bos)
                } catch (exception: Exception) {
                    emitter.onError(exception)
                }
            }
        }
    }
}