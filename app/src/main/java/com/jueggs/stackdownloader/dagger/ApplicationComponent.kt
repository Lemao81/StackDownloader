package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.activity.*
import com.jueggs.stackdownloader.fragment.*
import com.jueggs.stackdownloader.presenter.*
import com.jueggs.stackdownloader.render.QuestionRenderer
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface ApplicationComponent {
    fun inject(dependent: SearchCriteriaPresenter)
    fun inject(dependent: SearchResultPresenter)

    fun inject(dependent: SearchCriteriaFragment)
    fun inject(dependent: SearchResultFragment)
    fun inject(dependent: MainActivity)
    fun inject(dependent: SearchActivity)
    fun inject(dependent: QuestionRenderer)
}