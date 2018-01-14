package com.jueggs.stackdownloader.retrofit

import com.jueggs.stackdownloader.models.QuestionShell
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface StackOverflowClient {
    @GET("questions")
    fun questions(@QueryMap queryParameter: Map<String, String>): Call<QuestionShell>
}