package com.jueggs.stackdownloader.dagger

import com.jueggs.stackdownloader.activity.MainActivity
import com.jueggs.stackdownloader.activity.SearchActivity
import com.jueggs.stackdownloader.fragment.SearchCriteriaFragment
import com.jueggs.stackdownloader.fragment.SearchResultFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [(PresenterModule::class)])
interface PresenterComponent {
    fun inject(dependent: SearchCriteriaFragment)
    fun inject(dependent: SearchResultFragment)
    fun inject(dependent: MainActivity)
    fun inject(dependent: SearchActivity)
}