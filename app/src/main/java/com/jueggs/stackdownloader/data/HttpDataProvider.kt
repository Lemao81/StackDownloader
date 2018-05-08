package com.jueggs.stackdownloader.data

import com.jueggs.data.DataProvider
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.data.retrofit.StackOverflowApi
import com.jueggs.data.retrofit.dto.*
import com.jueggs.stackdownloader.util.*
import com.jueggs.utils.extension.join
import com.jueggs.utils.logNetwork

class HttpDataProvider(private val api: StackOverflowApi) : DataProvider {
    override fun provideQuestionData(searchCriteria: SearchCriteria, onSuccess: (ItemShellDto<QuestionDto>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = searchCriteria.mapToQueryParameter().build()
        val call = api.fetchQuestions(queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellDto<QuestionDto>>({ onSuccess(it) }, { onFail(it) }))
    }

    override fun provideAnswerData(questionIds: List<Long>, onSuccess: (ItemShellDto<AnswerDto>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = QueryParameter.Builder().build()
        val call = api.fetchAnswersOfQuestions(questionIds.join(";"), queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellDto<AnswerDto>>({ onSuccess(it) }, { onFail(it) }))
    }
}