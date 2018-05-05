package com.jueggs.stackdownloader.data

import com.jueggs.stackdownloader.bo.SearchCriteria
import com.jueggs.stackdownloader.retrofit.StackOverflowApi
import com.jueggs.stackdownloader.retrofit.dto.*
import com.jueggs.stackdownloader.util.*
import com.jueggs.utils.extension.join
import com.jueggs.utils.logNetwork

class HttpDataProvider(private val api: StackOverflowApi) : DataProvider {
    override fun provideQuestionData(searchCriteria: SearchCriteria, onSuccess: (ItemShellData<QuestionData>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = searchCriteria.mapToQueryParameter().build()
        val call = api.fetchQuestions(queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellData<QuestionData>>({ onSuccess(it) }, { onFail(it) }))
    }

    override fun provideAnswerData(questionIds: List<Long>, onSuccess: (ItemShellData<AnswerData>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = QueryParameter.Builder().build()
        val call = api.fetchAnswersOfQuestions(questionIds.join(";"), queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellData<AnswerData>>({ onSuccess(it) }, { onFail(it) }))
    }
}