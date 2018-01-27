package com.jueggs.stackdownloader.retrofit

import com.jueggs.stackdownloader.retrofit.model.AnswerData
import com.jueggs.stackdownloader.retrofit.model.ItemShellData
import com.jueggs.stackdownloader.retrofit.model.QuestionData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface StackOverflowClient {
    @GET("questions")
    fun fetchQuestions(@QueryMap queryParameter: Map<String, String>): Call<ItemShellData<QuestionData>>

    @GET("questions/{ids}/answers")
    fun fetchAnswersOfQuestions(@Path("ids") questionIds: String, @QueryMap queryParameter: Map<String, String>): Call<ItemShellData<AnswerData>>
}