package com.jueggs.stackdownloader.dagger

import android.app.Application
import com.jueggs.stackdownloader.fragments.SearchResultFragment
import com.jueggs.stackdownloader.presenter.SearchResultPresenter
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.utils.STACKOVERFLOW_BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideStackOverflowClient(): StackOverflowClient {
        val client = OkHttpClient.Builder().build()
        val retrofitBuilder = Retrofit.Builder().baseUrl(STACKOVERFLOW_BASE_URL).addConverterFactory(GsonConverterFactory.create())
        val retrofit = retrofitBuilder.client(client).build()
        return retrofit.create(StackOverflowClient::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchResultPresenter(stackOverflowClient: StackOverflowClient): SearchResultPresenter<SearchResultFragment> {
        return SearchResultPresenter(application, stackOverflowClient)
    }
}