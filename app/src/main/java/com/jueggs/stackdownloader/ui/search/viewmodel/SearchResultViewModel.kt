package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import com.jueggs.domain.model.*

class SearchResultViewModel : ViewModel() {
    val questions = MutableLiveData<List<Question>>()
    val answers = MutableLiveData<Pair<Question, List<Answer>>>()

    fun onShowQuestion(question: Question) {

    }

    fun onDownload() {

    }
}