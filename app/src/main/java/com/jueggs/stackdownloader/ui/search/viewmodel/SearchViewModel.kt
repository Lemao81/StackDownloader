package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import android.content.Context
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.andutils.pairOf
import com.jueggs.data.*
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R

class SearchViewModel(context: Context, private val repository: Repository, private val dataProvider: DataProvider) : AndroidViewModel(context.applicationContext as App) {
    var availableTags: LiveData<List<String>> = repository.getAllTags()
    var questions: LiveData<List<Question>> = repository.getAllQuestions()

    val answers: MutableLiveData<LiveData<Pair<Question, List<Answer>>>> = MutableLiveData()
    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    val errors: MutableLiveData<Int> = MutableLiveData()
    val search: MutableLiveData<SearchCriteria> = MutableLiveData()

    var limitTo: String = ""
    var score: String = ""
    var tag: String = ""
    var orderType: Int = 0
    var sortType: Int = 0

    fun onAddTag() {
        availableTags.value?.let {
            if (it.contains(tag))
                selectedTags.value = selectedTags.value?.apply { add(tag) }
            else
                errors.value = R.string.error_nonexistent_tag
        }
    }

    suspend fun onStartSearch() {
        val searchCriteria = SearchCriteria(limitTo, score, orderType, sortType, availableTags.value)
        search.value = searchCriteria

        if (getApplication<App>().isNetworkConnected()) {
            dataProvider.fetchQuestions(searchCriteria).subscribe { questions -> repository.addQuestions(questions) }
        }
    }

    fun onShowQuestion(question: Question) {
        answers.value = Transformations.map(repository.getAnswersOfQuestion(question.id), { answers -> pairOf(question, answers) })
    }

    fun onDownload() {
        if (getApplication<App>().isNetworkConnected()) {

        } else
            errors.value = R.string.error_no_network
    }
}