package com.jueggs.stackdownloader

import com.jueggs.data.*
import com.jueggs.data.retrofit.StackOverflowApi
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import okhttp3.OkHttpClient
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var appModule = applicationContext {
    bean { AppDatabase.getInstance(get()).answerDao() }
    bean { AppDatabase.getInstance(get()).questionDao() }
    bean { AppDatabase.getInstance(get()).tagDao() }
    bean { AppDatabase.getInstance(get()).ownerDao() }
    bean {
        val client = OkHttpClient.Builder().build()
        val retrofitBuilder = Retrofit.Builder().baseUrl(STACKOVERFLOW_BASE_URL).addConverterFactory(GsonConverterFactory.create())
        val retrofit = retrofitBuilder.client(client).build()
        retrofit.create(StackOverflowApi::class.java) as StackOverflowApi
    }
    bean { NetworkDataProvider(get(), get()) }

    viewModel { SearchViewModel(get(), get(), get()) }
}