package com.jueggs.stackdownloader.data

import com.jueggs.stackdownloader.model.*
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.retrofit.model.AnswerData
import com.jueggs.stackdownloader.retrofit.model.ItemShellData
import com.jueggs.stackdownloader.retrofit.model.QueryParameter
import com.jueggs.stackdownloader.retrofit.model.QuestionData
import com.jueggs.stackdownloader.util.RetrofitCallbackAdapter
import com.jueggs.stackdownloader.util.join
import com.jueggs.stackdownloader.util.mapToQueryParameter
import com.jueggs.utils.logNetwork

class HttpDataProvider(private val client: StackOverflowClient) : DataProvider {
    override fun provideQuestionData(searchCriteria: SearchCriteria, onSuccess: (ItemShellData<QuestionData>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = searchCriteria.mapToQueryParameter().build()
        val call = client.fetchQuestions(queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellData<QuestionData>>({ onSuccess(it) }, { onFail(it) }))
    }

    override fun provideAnswerData(questionIds: List<Long>, onSuccess: (ItemShellData<AnswerData>) -> Unit, onFail: (String) -> Unit) {
        val queryParam = QueryParameter.Builder().build()
        val call = client.fetchAnswersOfQuestions(questionIds.join(";"), queryParam.build())
        logNetwork(call.request().url())
        call.enqueue(RetrofitCallbackAdapter<ItemShellData<AnswerData>>({ onSuccess(it) }, { onFail(it) }))
    }
}