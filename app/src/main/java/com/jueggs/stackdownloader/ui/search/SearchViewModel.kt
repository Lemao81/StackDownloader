package com.jueggs.stackdownloader.ui.search

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import androidx.core.content.edit
import com.jueggs.data.Repository
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.usecase.*
import org.jetbrains.anko.defaultSharedPreferences
import java.util.*

class SearchViewModel(
        application: Application,
        repository: Repository,
        private val editTagUseCase: EditTagUseCase,
        private val startSearchUseCase: StartSearchUseCase,
        private val showQuestionUseCase: ShowQuestionUseCase,
        private val downloadUseCase: DownloadUseCase,
        private val setPeriodUseCase: SetPeriodUseCase,
        private val editDateUseCase: EditDateUseCase,
        private val initialStartUseCase: InitialStartUseCase) : AndroidViewModel(application) {

    val app: App
        get() = getApplication()

    var isDataDownloaded: Boolean
        get() = app.defaultSharedPreferences.getBoolean(PREFS_DATA_DOWNLOADED, false)
        set(value) = app.defaultSharedPreferences.edit { putBoolean(PREFS_DATA_DOWNLOADED, value) }

    var availableTags: LiveData<List<Tag>> = repository.getAllTags()
    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    var questions: MutableLiveData<List<Question>> = MutableLiveData()
    val answers: MutableLiveData<Pair<Question, List<Answer>>> = MutableLiveData()
    val errors: MutableLiveData<Int> = MutableLiveData()

    val onSearch: MutableLiveData<Unit> = MutableLiveData()
    val onEditFromDate: MutableLiveData<Unit> = MutableLiveData()
    val onEditToDate: MutableLiveData<Unit> = MutableLiveData()
    val onHideKeyboard: MutableLiveData<Unit> = MutableLiveData()

    val tag: MutableLiveData<CharSequence> = MutableLiveData()
    val orderType: MutableLiveData<Int> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val fromDate: ObservableField<Date> = ObservableField(Date())
    val toDate: ObservableField<Date> = ObservableField(Date())

    fun onAddTag() = editTagUseCase.add(this)

    fun onRemoveTag(tagName: String) = editTagUseCase.remove(this, tagName)

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