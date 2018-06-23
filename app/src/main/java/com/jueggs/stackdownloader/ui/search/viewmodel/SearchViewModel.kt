package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import androidx.core.content.edit
import com.jueggs.andutils.extension.fire
import com.jueggs.data.repository.LiveRepository
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.usecase.*
import org.jetbrains.anko.defaultSharedPreferences
import org.joda.time.DateTime
import java.util.*

class SearchViewModel(
        application: Application,
        liveRepository: LiveRepository,
        private val searchUseCase: SearchUseCase,
        private val addTagUseCase: AddTagUseCase,
        private val showQuestionUseCase: ShowQuestionUseCase,
        private val downloadDataUseCase: DownloadDataUseCase,
        private val initialStartUseCase: InitialStartUseCase,
        private val deleteDataUseCase: DeleteDataUseCase
) : AndroidViewModel(application) {
    private val searchInput: MutableLiveData<SearchCriteria> = MutableLiveData()
    private val addTagInput: MutableLiveData<CharSequence?> = MutableLiveData()
    private val showQuestionInput: MutableLiveData<Question> = MutableLiveData()
    private val downloadDataInput: MutableLiveData<Unit> = MutableLiveData()
    private val deleteDataInput: MutableLiveData<Unit> = MutableLiveData()
    val questions = liveRepository.getAllQuestionsIncludingOwnerAndTags()
    val availableTags = liveRepository.getAllTagNames()
    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    val tag: MutableLiveData<CharSequence> = MutableLiveData()
    val orderType: MutableLiveData<Int> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val fromDate: ObservableField<Date> = ObservableField(DateTime().minusWeeks(1).toDate())
    val toDate: ObservableField<Date> = ObservableField(Date())

    var isQuestionsDownloaded = false
    var isDataDownloaded: Boolean
        get() = getApplication<App>().defaultSharedPreferences.getBoolean(PREFS_DATA_DOWNLOADED, false)
        set(value) = getApplication<App>().defaultSharedPreferences.edit { putBoolean(PREFS_DATA_DOWNLOADED, value) }

    fun onAddTag() = addTagInput.postValue(tag.value)
    fun onRemoveTag(tagName: String) = selectedTags.postValue(selectedTags.value?.apply { remove(tagName) })
    fun onStartSearch() = searchInput.postValue(SearchCriteria(orderType.value, sortType.value, selectedTags.value, fromDate.get(), toDate.get()))
    fun onShowQuestion(question: Question) = showQuestionInput.postValue(question)
    fun onDownload() = downloadDataInput.fire()
    fun onDeleteData() = deleteDataInput.fire()

    fun onInitialStart() {
        initialStartUseCase.execute(UseCase.Request)
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

    fun getSearchResult(): LiveData<UseCaseResult> = Transformations.switchMap(searchInput) { searchUseCase.execute(SearchRequest(it)) }
    fun getAddTagResult(): LiveData<UseCaseResult> = Transformations.switchMap(addTagInput) { addTagUseCase.execute(AddTagRequest(it, selectedTags.value)) }
    fun getShowQuestionResult(): LiveData<UseCaseResult> = Transformations.switchMap(showQuestionInput) { showQuestionUseCase.execute(ShowQuestionRequest(it, isDataDownloaded)) }
    fun getDownloadDataResult(): LiveData<UseCaseResult> = Transformations.switchMap(downloadDataInput) { downloadDataUseCase.execute(UseCase.Request) }
    fun getDeleteDataResult(): LiveData<UseCaseResult> = Transformations.switchMap(deleteDataInput) { deleteDataUseCase.execute(UseCase.Request) }
}