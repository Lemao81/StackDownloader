package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.fragments.SearchCriteriaFragment
import com.jueggs.stackdownloader.fragments.SearchResultFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface ApplicationComponent {
    fun inject(fragment: SearchCriteriaFragment)
    fun inject(fragment: SearchResultFragment)
}