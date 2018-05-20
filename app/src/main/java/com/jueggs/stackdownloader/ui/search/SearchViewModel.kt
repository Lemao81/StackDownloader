package com.jueggs.stackdownloader.ui.search

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.andutils.pairOf
import com.jueggs.data.*
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.usecase.AddTagUseCase
import kotlinx.coroutines.experimental.async
import org.joda.time.DateTime
import java.util.*

class SearchViewModel(
        application: Application,
        private val repository: Repository,
        private val dataProvider: DataProvider,
        private val addTagUseCase: AddTagUseCase) : AndroidViewModel(application) {

    var availableTags: LiveData<List<Tag>> = repository.getAllTags()
    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    var questions: LiveData<List<Question>> = repository.getAllQuestions()
    val answers: MutableLiveData<LiveData<Pair<Question, List<Answer>>>> = MutableLiveData()
    val errors: MutableLiveData<Int> = MutableLiveData()

    val onSearch: MutableLiveData<SearchCriteria> = MutableLiveData()
    val onEditFromDate: MutableLiveData<Unit> = MutableLiveData()
    val onEditToDate: MutableLiveData<Unit> = MutableLiveData()

    val tag: MutableLiveData<CharSequence> = MutableLiveData()
    val orderType: MutableLiveData<Int> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val fromDate: ObservableField<Date> = ObservableField(Date())
    val toDate: ObservableField<Date> = ObservableField(Date())

    fun onAddTag() = addTagUseCase.go(this)

    fun onInitialStart() {
        if (getApplication<App>().isNetworkConnected()) {
            async { dataProvider.fetchTags().subscribe { tags -> repository.addTags(tags) } }
        }
    }

    fun onStartSearch() {
        val searchCriteria = SearchCriteria(orderType.value, sortType.value, selectedTags.value, fromDate.get(), toDate.get())
        onSearch.value = searchCriteria

        if (getApplication<App>().isNetworkConnected()) {
            async { dataProvider.fetchQuestions(searchCriteria).subscribe { questions -> repository.addQuestions(questions) } }
        }
    }

    fun onShowQuestion(question: Question) {
        answers.value = Transformations.map(repository.getAnswersOfQuestion(question.id), { answers -> pairOf(question, answers) })
    }

    fun onEditFromDate() {
        onEditFromDate.value = Unit
    }

    fun onEditToDate() {
        onEditToDate.value = Unit
    }

    fun onDownload() {
        if (getApplication<App>().isNetworkConnected()) {

        } else
            errors.value = R.string.error_no_network
    }

    fun onToday() {
        fromDate.set(Date())
        toDate.set(Date())
    }

    fun onLastWeek() {
        fromDate.set(DateTime().minusWeeks(1).toDate())
        toDate.set(Date())
    }

    fun onLastMonth() {
        fromDate.set(DateTime().minusMonths(1).toDate())
        toDate.set(Date())
    }
}