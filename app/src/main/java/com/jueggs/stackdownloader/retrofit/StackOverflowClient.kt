package com.jueggs.stackdownloader.retrofit

import com.jueggs.stackdownloader.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface StackOverflowClient {
    @GET("questions")
    fun fetchQuestions(@QueryMap queryParameter: Map<String, String>): Call<ItemShellData<QuestionData>>

    @GET("questions/{id}/answers")
    fun fetchAnswersOfQuestion(@Path("id") questionId: Long, @QueryMap queryParameter: Map<String, String>): Call<ItemShellData<AnswerData>>
}