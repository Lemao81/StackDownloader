package com.jueggs.stackdownloader.ui.search

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.jueggs.data.Repository
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.ui.search.usecase.*
import java.util.*

class SearchViewModel(
        application: Application,
        repository: Repository,
        private val addTagUseCase: AddTagUseCase,
        private val startSearchUseCase: StartSearchUseCase,
        private val showQuestionUseCase: ShowQuestionUseCase,
        private val downloadUseCase: DownloadUseCase,
        private val setPeriodUseCase: SetPeriodUseCase,
        private val editDateUseCase: EditDateUseCase,
        private val initialStartUseCase: InitialStartUseCase) : AndroidViewModel(application) {

    var availableTags: LiveData<List<Tag>> = repository.getAllTags()
    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    var questions: LiveData<List<Question>> = repository.getAllQuestionsIncludingTags()
    val answers: MutableLiveData<LiveData<Pair<Question, List<Answer>>>> = MutableLiveData()
    val errors: MutableLiveData<Int> = MutableLiveData()

    val onSearch: MutableLiveData<SearchCriteria> = MutableLiveData()
    val onEditFromDate: MutableLiveData<Unit> = MutableLiveData()
    val onEditToDate: MutableLiveData<Unit> = MutableLiveData()
    val onHideKeyboard: MutableLiveData<Unit> = MutableLiveData()

    val tag: MutableLiveData<CharSequence> = MutableLiveData()
    val orderType: MutableLiveData<Int> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val fromDate: ObservableField<Date> = ObservableField(Date())
    val toDate: ObservableField<Date> = ObservableField(Date())

    fun onAddTag() = addTagUseCase.go(this)

    fun onInitialStart() = initialStartUseCase.go(this)

    fun onStartSearch() = startSearchUseCase.go(this)

    fun onShowQuestion(question: Question) = showQuestionUseCase.go(this, question)

    fun onEditFromDate() = editDateUseCase.from(this)

    fun onEditToDate() = editDateUseCase.to(this)

    fun onDownload() = downloadUseCase.go(this)

    fun onToday() = setPeriodUseCase.today(this)

    fun onLastWeek() = setPeriodUseCase.lastWeek(this)

    fun onLastMonth() = setPeriodUseCase.lastMonth(this)
}