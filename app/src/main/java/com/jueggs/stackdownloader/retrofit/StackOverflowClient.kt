package com.jueggs.stackdownloader.retrofit

import com.jueggs.stackdownloader.models.Answer
import com.jueggs.stackdownloader.models.ItemShell
import com.jueggs.stackdownloader.models.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface StackOverflowClient {
    @GET("questions")
    fun questions(@QueryMap queryParameter: Map<String, String>): Call<ItemShell<Question>>

    @GET("questions/{id}/answers")
    fun answersOfQuestion(@Path("id") questionId: Long, @QueryMap queryParameter: Map<String, String>): Call<ItemShell<Answer>>
}