package com.jueggs.stackdownloader.data

import com.jueggs.stackdownloader.model.*
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.utils.RetrofitCallbackAdapter
import com.jueggs.stackdownloader.utils.mapToQueryParameter
import com.jueggs.utils.logNetwork
import javax.inject.Inject

class HttpDataProvider @Inject constructor(private val client: StackOverflowClient) : DataProvider {
    override fun provideQuestionData(searchCriteria: SearchCriteria, onSuccess: (ItemShellData<QuestionData>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = searchCriteria.mapToQueryParameter().build()
        val call = client.fetchQuestions(queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellData<QuestionData>>({ onSuccess(it) }, { onFail(it) }))
    }

    override fun provideAnswerData(questionId: Long, onSuccess: (ItemShellData<AnswerData>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = QueryParameter.Builder().build()
        val call = client.fetchAnswersOfQuestion(questionId, queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellData<AnswerData>>({ onSuccess(it) }, { onFail(it) }))
    }
}