package com.jueggs.data

import android.content.Context
import com.jueggs.data.mapper.bo
import com.jueggs.data.retrofit.StackOverflowApi
import com.jueggs.data.retrofit.dto.*
import com.jueggs.domain.model.*
import com.jueggs.jutils.extension.join
import io.reactivex.Single

class NetworkDataProvider(private val context: Context, private val apiImpl: StackOverflowApi) : DataProvider {

    override suspend fun fetchQuestions(searchCriteria: SearchCriteria): Single<List<Question>> {
        val queryParams = searchCriteria.mapToQueryParameter(context).getKeyValueMap()
        val data = apiImpl.fetchQuestions(queryParams).await()
        val bos = data.items?.map { it.bo }
        return Single.just(bos ?: emptyList())
    }

    override suspend fun fetchAnswers(questionIds: List<Long>): Single<List<Answer>> {
        val idJoin = questionIds.join(";")
        val queryParams = QueryParameter().getKeyValueMap()
        val data = apiImpl.fetchAnswersOfQuestions(idJoin, queryParams).await()
        val bos = data.items?.map { it.bo }
        return Single.just(bos ?: emptyList())
    }
}