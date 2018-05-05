package com.jueggs.stackdownloader.retrofit

import com.jueggs.stackdownloader.retrofit.dto.*
import retrofit2.Call
import retrofit2.http.*

interface StackOverflowApi {
    @GET("questions")
    fun fetchQuestions(@QueryMap queryParameter: Map<String, String>): Call<ItemShellData<QuestionData>>

    @GET("questions/{ids}/answers")
    fun fetchAnswersOfQuestions(@Path("ids") questionIds: String, @QueryMap queryParameter: Map<String, String>): Call<ItemShellData<AnswerData>>
}