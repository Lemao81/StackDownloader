package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.activity.MainActivity
import com.jueggs.stackdownloader.activity.SearchActivity
import com.jueggs.stackdownloader.fragment.SearchCriteriaFragment
import com.jueggs.stackdownloader.fragment.SearchResultFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface ApplicationComponent {
    fun inject(dependent: SearchCriteriaFragment)
    fun inject(dependent: SearchResultFragment)
    fun inject(dependent: MainActivity)
    fun inject(dependent: SearchActivity)
}