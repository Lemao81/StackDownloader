package com.jueggs.stackdownloader.utils

import com.jueggs.utils.logNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                    logNetwork(response.errorBody()!!.string())
                "Call was not successful"
            }
        } else "No response retrieved"
        onFail(errorMessage)
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        if (t?.message != null) logNetwork(t.message!!)
        onFail("Call to fetch data failed")
    }
}