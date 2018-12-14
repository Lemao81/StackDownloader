package com.jueggs.data.dataprovider

import android.content.Context
import com.jueggs.data.SORT_POPULAR
import com.jueggs.data.mapper.mapToBo
import com.jueggs.data.retrofit.StackOverflowApi
import com.jueggs.data.retrofit.dto.QueryParameter
import com.jueggs.data.retrofit.dto.mapToQueryParameter
import com.jueggs.domain.DataProvider
import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.domain.model.Tag
import com.jueggs.jutils.extension.join
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NetworkDataProvider(private val context: Context, private val apiImpl: StackOverflowApi) : DataProvider {

    override fun fetchTags(): Single<List<Tag>> {
        return Single.create { emitter ->
            // TODO remove globalscope
            GlobalScope.launch {
                try {
                    val queryParams = QueryParameter().also {
                        it.pageSize = 100
                        it.sort = SORT_POPULAR
                    }.asMap(false)
                    val data = apiImpl.fetchTags(queryParams).await()
                    val bos = data.items?.map { it.mapToBo() } ?: throw NoDataException()

                    emitter.onSuccess(bos)
                } catch (exception: Exception) {
                    emitter.onError(exception)
                }
            }
        }
    }

    override fun fetchQuestions(searchCriteria: SearchCriteria): Single<List<Question>> {
        return Single.create { emitter ->
            GlobalScope.launch {
                try {
                    val queryParams = searchCriteria.mapToQueryParameter(this@NetworkDataProvider.context).asMap()
                    val data = apiImpl.fetchQuestions(queryParams).await()
                    val bos = data.items?.map { it.mapToBo() } ?: throw NoDataException()

                    emitter.onSuccess(bos)
                } catch (exception: Exception) {
                    emitter.onError(exception)
                }
            }
        }
    }

    override fun fetchAnswers(questionIds: List<Long>): Single<List<Answer>> {
        return Single.create { emitter ->
            GlobalScope.launch {
                try {
                    val idJoin = questionIds.join(";")
                    val queryParams = QueryParameter().asMap()
                    val data = apiImpl.fetchAnswersOfQuestions(idJoin, queryParams).await()
                    val bos = data.items?.map { it.mapToBo() } ?: throw NoDataException()

                    emitter.onSuccess(bos)
                } catch (exception: Exception) {
                    emitter.onError(exception)
                }
            }
        }
    }
}