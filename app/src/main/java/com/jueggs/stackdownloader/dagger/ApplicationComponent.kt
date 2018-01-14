package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.fragments.SearchCriteriaFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface ApplicationComponent {
    fun inject(fragment: SearchCriteriaFragment)
}