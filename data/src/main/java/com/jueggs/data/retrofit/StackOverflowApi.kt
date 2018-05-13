package com.jueggs.data.retrofit

import com.jueggs.data.retrofit.dto.*
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.*

interface StackOverflowApi {
    @GET("tags")
    fun fetchTags(@QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<TagDto>>

    @GET("questions")
    fun fetchQuestions(@QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<QuestionDto>>

    @GET("questions/{ids}/answers")
    fun fetchAnswersOfQuestions(@Path("ids") questionIds: String, @QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<AnswerDto>>
}