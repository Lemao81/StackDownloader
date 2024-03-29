package com.jueggs.stackdownloader

import com.github.simonpercic.oklog3.OkLogInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jueggs.andutils.util.AppMode
import com.jueggs.data.AppDatabase
import com.jueggs.data.STACKOVERFLOW_BASE_URL
import com.jueggs.data.dataprovider.NetworkDataProvider
import com.jueggs.data.repository.LiveRepository
import com.jueggs.data.repository.RoomLiveRepository
import com.jueggs.data.repository.RoomRepository
import com.jueggs.data.retrofit.StackOverflowApi
import com.jueggs.domain.DataProvider
import com.jueggs.domain.Repository
import com.jueggs.stackdownloader.ui.search.delegate.SinglePaneSearchActivityDelegate
import com.jueggs.stackdownloader.ui.search.delegate.SinglePaneSearchResultFragmentDelegate
import com.jueggs.stackdownloader.ui.search.delegate.TwoPaneSearchActivityDelegate
import com.jueggs.stackdownloader.ui.search.delegate.TwoPaneSearchResultFragmentDelegate
import com.jueggs.stackdownloader.ui.search.usecase.AddTagUseCase
import com.jueggs.stackdownloader.ui.search.usecase.DeleteDataUseCase
import com.jueggs.stackdownloader.ui.search.usecase.DownloadDataUseCase
import com.jueggs.stackdownloader.ui.search.usecase.InitialStartUseCase
import com.jueggs.stackdownloader.ui.search.usecase.SearchUseCase
import com.jueggs.stackdownloader.ui.search.usecase.ShowQuestionUseCase
import com.jueggs.stackdownloader.ui.search.view.SearchActivity
import com.jueggs.stackdownloader.ui.search.view.SearchResultFragment
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var appModule = module {
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
    bean { RoomLiveRepository(get(), get(), get()) as LiveRepository }
    bean { NetworkDataProvider(get(), get()) as DataProvider }

    bean(SearchActivity::class.simpleName ?: throw IllegalStateException()) { if (AppMode.singlePane) SinglePaneSearchActivityDelegate() else TwoPaneSearchActivityDelegate() }
    bean(SearchResultFragment::class.simpleName ?: throw IllegalStateException()) { if (AppMode.singlePane) SinglePaneSearchResultFragmentDelegate() else TwoPaneSearchResultFragmentDelegate() }

    bean { AddTagUseCase(get()) }
    bean { InitialStartUseCase(get(), get(), get()) }
    bean { ShowQuestionUseCase(get()) }
    bean { DownloadDataUseCase(get(), get(), get()) }
    bean { SearchUseCase(get(), get(), get()) }
    bean { DeleteDataUseCase(get()) }

    viewModel { SearchViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
}