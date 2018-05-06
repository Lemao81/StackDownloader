package com.jueggs.stackdownloader

import com.jueggs.data.AppDatabase
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchCriteriaViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

var appModule = applicationContext {
    bean { this }
    bean { AppDatabase.getInstance(get()).answerDao() }
    bean { AppDatabase.getInstance(get()).questionDao() }
    bean { AppDatabase.getInstance(get()).tagDao() }
    bean { AppDatabase.getInstance(get()).ownerDao() }

    viewModel { SearchCriteriaViewModel() }
}