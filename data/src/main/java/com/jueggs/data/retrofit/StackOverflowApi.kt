package com.jueggs.data.retrofit

import com.jueggs.data.retrofit.dto.*
import kotlinx.coroutines.experimental.Deferred
import retrofit2.*
import retrofit2.http.*

interface StackOverflowApi {
    @GET("questions")
    fun fetchQuestions(@QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<QuestionDto>>

    @GET("questions/{ids}/answers")
    fun fetchAnswersOfQuestions(@Path("ids") questionIds: String, @QueryMap queryParameter: Map<String, String>): Deferred<ItemShellDto<AnswerDto>>
}

open class RetrofitCallbackAdapter<T>(private val onSuccess: (T) -> Unit, private val onFail: (String) -> Unit) : Callback<T> {
    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        var errorMessage = "Fetching data failed: "
        errorMessage += if (response != null) {
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    onSuccess(result)
                    return
                }
                "Response without body"
            } else {
                if (response.errorBody() != null)
//                    logNetwork(response.errorBody()!!.string())
                "Call was not successful"
            }
        } else "No response retrieved"
        onFail(errorMessage)
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
//        if (t?.message != null) logNetwork(t.message!!)
        onFail("Call to fetch data failed")
    }
}