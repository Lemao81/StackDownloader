package com.jueggs.data.retrofit

import com.jueggs.data.retrofit.dto.*
import retrofit2.Call
import retrofit2.http.*

interface StackOverflowApi {
    @GET("questions")
    fun fetchQuestions(@QueryMap queryParameter: Map<String, String>): Call<ItemShellDto<QuestionDto>>

    @GET("questions/{ids}/answers")
    fun fetchAnswersOfQuestions(@Path("ids") questionIds: String, @QueryMap queryParameter: Map<String, String>): Call<ItemShellDto<AnswerDto>>
}