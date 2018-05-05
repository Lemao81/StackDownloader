package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.data.*
import com.jueggs.stackdownloader.data.entity.*
import com.jueggs.stackdownloader.factory.RendererFactory
import com.jueggs.stackdownloader.presenter.*
import com.jueggs.stackdownloader.presenter.interfaces.*
import com.jueggs.stackdownloader.retrofit.StackOverflowApi
import com.jueggs.stackdownloader.util.STACKOVERFLOW_BASE_URL
import com.jueggs.utils.DateRenderer
import dagger.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides @Singleton
    fun provideApplication(): App = app

    @Provides @Singleton
    fun provideStackOverflowClient(): StackOverflowApi {
        val client = OkHttpClient.Builder().build()
        val retrofitBuilder = Retrofit.Builder().baseUrl(STACKOVERFLOW_BASE_URL).addConverterFactory(GsonConverterFactory.create())
        val retrofit = retrofitBuilder.client(client).build()
        return retrofit.create(StackOverflowApi::class.java)
    }

    @Provides @Singleton
    fun provideDataProvider(stackOverflowApi: StackOverflowApi): DataProvider = HttpDataProvider(stackOverflowApi)

    @Provides @Singleton
    fun provideDatabaseInfo(): DatabaseInfo = DatabaseInfo("stackdownloader.db")

    @Provides @Singleton
    fun provideDaoSession(databaseInfo: DatabaseInfo): DaoSession = DaoMaster(DbOpenHelper(app, databaseInfo.name).writableDb).newSession()

    @Provides @Singleton
    fun provideDateRenderer(): DateRenderer = DateRenderer()

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator(app)

    @Provides @Singleton
    fun provideMainPresenter(): IMainPresenter = MainPresenter()

    @Provides @Singleton
    fun provideSearchPresenter(): ISearchPresenter = SearchPresenter()

    @Provides @Singleton
    fun provideSearchCriteriaPresenter(): ISearchCriteriaPresenter = SearchCriteriaPresenter()

    @Provides @Singleton
    fun provideSearchResultPresenter(): ISearchResultPresenter = SearchResultPresenter()

    @Provides @Singleton
    fun provideRendererFactory(dateRenderer: DateRenderer): RendererFactory = RendererFactory(dateRenderer)
}