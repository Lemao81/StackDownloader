package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import androidx.core.content.edit
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jueggs.andutils.extension.defaultSharedPrefs
import com.jueggs.andutils.extension.fire
import com.jueggs.data.repository.LiveRepository
import com.jueggs.domain.model.Question
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.PREFS_DATA_DOWNLOADED
import com.jueggs.stackdownloader.ui.search.usecase.AddTagRequest
import com.jueggs.stackdownloader.ui.search.usecase.AddTagUseCase
import com.jueggs.stackdownloader.ui.search.usecase.DeleteDataUseCase
import com.jueggs.stackdownloader.ui.search.usecase.DownloadDataUseCase
import com.jueggs.stackdownloader.ui.search.usecase.InitialStartUseCase
import com.jueggs.stackdownloader.ui.search.usecase.SearchRequest
import com.jueggs.stackdownloader.ui.search.usecase.SearchUseCase
import com.jueggs.stackdownloader.ui.search.usecase.ShowQuestionRequest
import com.jueggs.stackdownloader.ui.search.usecase.ShowQuestionUseCase
import com.jueggs.stackdownloader.ui.search.usecase.UseCase
import com.jueggs.stackdownloader.ui.search.usecase.UseCaseResult
import org.joda.time.LocalDate

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
    val fromDate: ObservableField<LocalDate> = ObservableField(LocalDate.now().minusWeeks(1))
    val toDate: ObservableField<LocalDate> = ObservableField(LocalDate.now())

    var isQuestionsDownloaded = false
    var isDataDownloaded: Boolean
        get() = getApplication<App>().defaultSharedPrefs.getBoolean(PREFS_DATA_DOWNLOADED, false)
        set(value) = getApplication<App>().defaultSharedPrefs.edit { putBoolean(PREFS_DATA_DOWNLOADED, value) }

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
        val now = LocalDate.now()
        fromDate.set(now)
        toDate.set(now)
    }

    fun onLastWeek() {
        val now = LocalDate.now()
        fromDate.set(now.minusWeeks(1))
        toDate.set(now)
    }

    fun onLastMonth() {
        val now = LocalDate.now()
        fromDate.set(now.minusMonths(1))
        toDate.set(now)
    }

    val searchResult: LiveData<UseCaseResult> = Transformations.switchMap(searchInput) { searchUseCase.execute(SearchRequest(it)) }
    val addTagResult: LiveData<UseCaseResult> = Transformations.switchMap(addTagInput) { addTagUseCase.execute(AddTagRequest(it, selectedTags.value)) }
    val showQuestionResult: LiveData<UseCaseResult> = Transformations.switchMap(showQuestionInput) { showQuestionUseCase.execute(ShowQuestionRequest(it, isDataDownloaded)) }
    val downloadDataResult: LiveData<UseCaseResult> = Transformations.switchMap(downloadDataInput) { downloadDataUseCase.execute(UseCase.Request) }
    val deleteDataResult: LiveData<UseCaseResult> = Transformations.switchMap(deleteDataInput) { deleteDataUseCase.execute(UseCase.Request) }
}