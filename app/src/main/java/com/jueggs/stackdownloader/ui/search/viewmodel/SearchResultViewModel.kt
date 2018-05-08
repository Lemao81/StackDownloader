package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import android.content.Context
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.App

class SearchResultViewModel(context: Context) : AndroidViewModel(context.applicationContext as App) {
    val questions = MutableLiveData<List<Question>>()
    val answers = MutableLiveData<Pair<Question, List<Answer>>>()

    var searchCriteria: SearchCriteria? = null

    fun startSearch() {
        if (getApplication<App>().isNetworkConnected()) {

        } else {

        }
    }

    fun onShowQuestion(question: Question) {

    }

    fun onDownload() {

    }
}