package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.presenter.SearchCriteriaPresenter
import com.jueggs.stackdownloader.presenter.SearchResultPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (PresenterModule::class)])
interface ApplicationComponent {
    fun inject(dependent: SearchCriteriaPresenter)
    fun inject(dependent: SearchResultPresenter)
}