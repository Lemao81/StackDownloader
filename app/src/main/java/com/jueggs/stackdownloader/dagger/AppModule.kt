package com.jueggs.stackdownloader.dagger

import android.app.Application
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.Navigator
import com.jueggs.stackdownloader.data.DataProvider
import com.jueggs.stackdownloader.data.DbOpenHelper
import com.jueggs.stackdownloader.data.HttpDataProvider
import com.jueggs.stackdownloader.data.model.DaoMaster
import com.jueggs.stackdownloader.data.model.DaoSession
import com.jueggs.stackdownloader.data.model.DatabaseInfo
import com.jueggs.stackdownloader.presenter.*
import com.jueggs.stackdownloader.presenter.interfaces.IMainPresenter
import com.jueggs.stackdownloader.presenter.interfaces.ISearchCriteriaPresenter
import com.jueggs.stackdownloader.presenter.interfaces.ISearchPresenter
import com.jueggs.stackdownloader.presenter.interfaces.ISearchResultPresenter
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.util.STACKOVERFLOW_BASE_URL
import com.jueggs.utils.DateRenderer
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
    fun provideDataProvider(stackOverflowClient: StackOverflowClient): DataProvider = HttpDataProvider(stackOverflowClient)

    @Provides @Singleton
    fun provideDatabaseInfo(): DatabaseInfo = DatabaseInfo("stackdownloader.db")

    @Provides @Singleton
    fun provideDaoSession(databaseInfo: DatabaseInfo): DaoSession = DaoMaster(DbOpenHelper(app, databaseInfo.name).writableDb).newSession()

    @Provides @Singleton
    fun provideDateRenderer(): DateRenderer = DateRenderer()

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator(app)
}