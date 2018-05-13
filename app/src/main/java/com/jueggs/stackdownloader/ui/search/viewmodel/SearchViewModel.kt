package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.andutils.pairOf
import com.jueggs.andutils.util.AppMode
import com.jueggs.data.*
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R
import kotlinx.coroutines.experimental.async
import java.util.*

class SearchViewModel(application: Application, private val repository: Repository, private val dataProvider: DataProvider) : AndroidViewModel(application) {
    var availableTags: LiveData<List<String>> = repository.getAllTags()
    var questions: LiveData<List<Question>> = repository.getAllQuestions()

    val answers: MutableLiveData<LiveData<Pair<Question, List<Answer>>>> = MutableLiveData()
    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    val errors: MutableLiveData<Int> = MutableLiveData()
    val search: MutableLiveData<SearchCriteria> = MutableLiveData()
    val showHomeButton: MutableLiveData<Boolean> = MutableLiveData()
    val editFromDate: MutableLiveData<Unit> = MutableLiveData()
    val editToDate: MutableLiveData<Unit> = MutableLiveData()

    var tag: String = ""
    var orderType: Int = 0
    var sortType: Int = 0
    var from: Date = Date()
    var to: Date = Date()

    fun onAddTag() {
        availableTags.value?.let {
            if (it.contains(tag))
                selectedTags.value = selectedTags.value?.apply { add(tag) }
            else
                errors.value = R.string.error_nonexistent_tag
        }
    }

    fun onStartSearch() {
        val searchCriteria = SearchCriteria(orderType, sortType, availableTags.value, from, to)
        search.value = searchCriteria

        if (getApplication<App>().isNetworkConnected()) {
            async { dataProvider.fetchQuestions(searchCriteria).subscribe { questions -> repository.addQuestions(questions) } }
        }
    }

    fun onShowQuestion(question: Question) {
        answers.value = Transformations.map(repository.getAnswersOfQuestion(question.id), { answers -> pairOf(question, answers) })

        if (AppMode.twoPane)
            showHomeButton.value = true
    }

    fun onEditFromDate() {
        editFromDate.value = Unit
    }

    fun onEditToDate() {
        editToDate.value = Unit
    }

    fun hideHomeButton() {
        showHomeButton.value = false
    }

    fun onDownload() {
        if (getApplication<App>().isNetworkConnected()) {

        } else
            errors.value = R.string.error_no_network
    }
}