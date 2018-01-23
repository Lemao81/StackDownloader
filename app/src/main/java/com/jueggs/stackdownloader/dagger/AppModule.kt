package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.data.DataProvider
import com.jueggs.stackdownloader.data.DbOpenHelper
import com.jueggs.stackdownloader.data.HttpDataProvider
import com.jueggs.stackdownloader.model.DaoMaster
import com.jueggs.stackdownloader.model.DaoSession
import com.jueggs.stackdownloader.model.DatabaseInfo
import com.jueggs.stackdownloader.presenter.MainPresenter
import com.jueggs.stackdownloader.presenter.SearchCriteriaPresenter
import com.jueggs.stackdownloader.presenter.SearchPresenter
import com.jueggs.stackdownloader.presenter.SearchResultPresenter
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.util.STACKOVERFLOW_BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides @Singleton
    fun provideApplication(): App = app

    @Provides @Singleton
    fun provideStackOverflowClient(): StackOverflowClient {
        val client = OkHttpClient.Builder().build()
        val retrofitBuilder = Retrofit.Builder().baseUrl(STACKOVERFLOW_BASE_URL).addConverterFactory(GsonConverterFactory.create())
        val retrofit = retrofitBuilder.client(client).build()
        return retrofit.create(StackOverflowClient::class.java)
    }

    @Provides @Singleton
    fun provideDataProvider(stackOverflowClient: StackOverflowClient): DataProvider {
        return HttpDataProvider(stackOverflowClient)
    }

    @Provides @Singleton
    fun provideSearchResultPresenter(dataProvider: DataProvider): SearchResultPresenter {
        return SearchResultPresenter(app, dataProvider)
    }

    @Provides @Singleton
    fun provideSearchCriteriaPresenter(dataProvider: DataProvider, searchResultPresenter: SearchResultPresenter): SearchCriteriaPresenter {
        return SearchCriteriaPresenter(app, dataProvider, searchResultPresenter)
    }

    @Provides @Singleton
    fun provideMainPresenter(): MainPresenter {
        return MainPresenter()
    }

    @Provides @Singleton
    fun provideSearchPresenter(): SearchPresenter {
        return SearchPresenter()
    }

    @Provides @Singleton
    fun provideDatabaseInfo(): DatabaseInfo {
        return DatabaseInfo("stackdownloader.db")
    }

    @Provides @Singleton
    fun provideDaoSession(databaseInfo: DatabaseInfo): DaoSession {
        return DaoMaster(DbOpenHelper(app, databaseInfo.name).writableDb).newSession()
    }
}