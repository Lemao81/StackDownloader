package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.presenter.MainPresenter
import com.jueggs.stackdownloader.presenter.SearchCriteriaPresenter
import com.jueggs.stackdownloader.presenter.SearchPresenter
import com.jueggs.stackdownloader.presenter.SearchResultPresenter
import com.jueggs.stackdownloader.presenter.interfaces.IMainPresenter
import com.jueggs.stackdownloader.presenter.interfaces.ISearchCriteriaPresenter
import com.jueggs.stackdownloader.presenter.interfaces.ISearchPresenter
import com.jueggs.stackdownloader.presenter.interfaces.ISearchResultPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides @Singleton
    fun provideMainPresenter(): IMainPresenter = MainPresenter()

    @Provides @Singleton
    fun provideSearchPresenter(): ISearchPresenter = SearchPresenter()

    @Provides @Singleton
    fun provideSearchCriteriaPresenter(): ISearchCriteriaPresenter = SearchCriteriaPresenter()

    @Provides @Singleton
    fun provideSearchResultPresenter(): ISearchResultPresenter = SearchResultPresenter()
}