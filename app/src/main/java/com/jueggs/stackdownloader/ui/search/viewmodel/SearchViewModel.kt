package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import androidx.core.content.edit
import com.jueggs.andutils.extension.fire
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

    private val downloadInput: MutableLiveData<Unit> = MutableLiveData()
    val downloadResult: LiveData<UseCaseResult> = Transformations.switchMap(downloadInput) { downloadUseCase.execute(DownloadRequest) }

    var isDataDownloaded: Boolean
        get() = getApplication<App>().defaultSharedPreferences.getBoolean(PREFS_DATA_DOWNLOADED, false)
        set(value) = getApplication<App>().defaultSharedPreferences.edit { putBoolean(PREFS_DATA_DOWNLOADED, value) }

    fun onInitialStart() {
        initialStartUseCase.execute(InitialStartRequest)
        criteriaViewModel.fromDate.set(DateTime().minusWeeks(1).toDate())
        criteriaViewModel.toDate.set(Date())

        //TODO for developing reasons, remove later
        criteriaViewModel.tag.value = "java"
    }

    fun onDownload() = downloadInput.fire()
}