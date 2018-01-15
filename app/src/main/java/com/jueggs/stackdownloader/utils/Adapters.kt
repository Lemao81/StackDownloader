package com.jueggs.stackdownloader.utils

import android.content.Context
import android.view.View
import com.jueggs.utils.logNetwork
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RetrofitCallbackAdapter<T>(private val _context: Context, private val action: (T) -> Unit) : Callback<T> {
    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response != null) {
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    action(result)
                    return
                }
            } else {
                if (response.errorBody() != null) {
                    logNetwork(response.errorBody()!!.string())
                }
            }
        }
        _context.longToast("Search brought no results")
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        if (t?.message != null) logNetwork(t.message!!)
        _context.longToast("Search brought no results")
    }
}