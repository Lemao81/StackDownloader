package com.jueggs.stackdownloader.utils

import android.content.Context
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RetrofitCallbackAdapter<T>(private val _context: Context, private val action: (T) -> Unit) : Callback<T> {
    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response != null) {
            val result = response.body()
            if (result != null) {
                action(result)
                return
            }
        }
        _context.longToast("Retrofit-Call brought no results")
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        var message = "Retrofit-Call failed"
        if (t != null) message += ": ${t.message}"
        _context.longToast(message)
    }

}