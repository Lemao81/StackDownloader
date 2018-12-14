package com.jueggs.data.retrofit

import com.jueggs.data.retrofit.dto.AnswerDto
import com.jueggs.data.retrofit.dto.ItemShellDto
import com.jueggs.data.retrofit.dto.QuestionDto
import com.jueggs.data.retrofit.dto.TagDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface StackOverflowApi {
    @GET("tags")
    fun fetchTags(@QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<TagDto>>

    @GET("questions")
    fun fetchQuestions(@QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<QuestionDto>>

    @GET("questions/{ids}/answers")
    fun fetchAnswersOfQuestions(@Path("ids") questionIds: String, @QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<AnswerDto>>
}