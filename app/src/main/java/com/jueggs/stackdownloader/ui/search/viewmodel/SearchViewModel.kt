package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import androidx.core.content.edit
import com.jueggs.andutils.extension.*
import com.jueggs.domain.usecase.InitialStartUseCase
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.usecase.*
import org.jetbrains.anko.defaultSharedPreferences
import org.joda.time.DateTime
import java.util.*

class SearchViewModel(
        application: Application,
        val criteriaViewModel: SearchCriteriaViewModel,
        val resultViewModel: SearchResultViewModel,
        private val downloadUseCase: DownloadUseCase,
        private val initialStartUseCase: InitialStartUseCase
) : AndroidViewModel(application) {

    val onToast: MediatorLiveData<Int> = MediatorLiveData()
    val onLongToast: MediatorLiveData<Int> = MediatorLiveData()
    val onShowProgress: MediatorLiveData<Boolean> = MediatorLiveData()

    private val downloadInput: MutableLiveData<Unit> = MutableLiveData()
    val downloadResult: LiveData<UseCaseResult> = Transformations.switchMap(downloadInput) { downloadUseCase.execute(DownloadRequest) }

    init {
        onLongToast.addSource(criteriaViewModel.onLongToast) { onLongToast.fire(it!!) }
        onLongToast.addSource(resultViewModel.onLongToast) { onLongToast.fire(it!!) }
        //TODO lib
        onShowProgress.addSource(criteriaViewModel.onShowProgress) { onShowProgress.value = it }
    }

    var isDataDownloaded: Boolean
        get() = getApplication<App>().defaultSharedPreferences.getBoolean(PREFS_DATA_DOWNLOADED, false)
        set(value) = getApplication<App>().defaultSharedPreferences.edit { putBoolean(PREFS_DATA_DOWNLOADED, value) }

    fun onInitialStart() {
        doWithNetworkConnection<App> {
            initialStartUseCase.execute()
        }
        criteriaViewModel.fromDate.set(DateTime().minusWeeks(1).toDate())
        criteriaViewModel.toDate.set(Date())

        //TODO for developing reasons, remove later
        criteriaViewModel.tag.value = "java"
    }

    fun onDownload() = downloadInput.fire()
}