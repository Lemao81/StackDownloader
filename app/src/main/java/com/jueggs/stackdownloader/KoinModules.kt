package com.jueggs.stackdownloader

import com.github.simonpercic.oklog3.OkLogInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jueggs.data.*
import com.jueggs.data.retrofit.StackOverflowApi
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import com.jueggs.stackdownloader.ui.search.usecase.*
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
    bean { AppDatabase.getInstance(get()).questionTagJoinDao() }
    bean {
        val okLogInterceptor = OkLogInterceptor.builder().build()
        val okHttpclient = OkHttpClient.Builder().addInterceptor(okLogInterceptor).build()
        val retrofitBuilder = Retrofit.Builder().baseUrl(STACKOVERFLOW_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory())
        val retrofit = retrofitBuilder.client(okHttpclient).build()
        retrofit.create(StackOverflowApi::class.java) as StackOverflowApi
    }
    bean { NetworkDataProvider(get(), get()) }
    bean { RoomRepository(get(), get(), get(), get(), get()) as Repository }
    bean { NetworkDataProvider(get(), get()) as DataProvider }

    bean { EditTagUseCase() }
    bean { InitialStartUseCase(get(), get()) }
    bean { StartSearchUseCase(get(), get()) }
    bean { ShowQuestionUseCase(get()) }
    bean { DownloadUseCase(get(), get()) }
    bean { SetPeriodUseCase() }
    bean { EditDateUseCase() }

    viewModel { SearchViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
}