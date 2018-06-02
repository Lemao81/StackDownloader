package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import androidx.core.content.edit
import com.jueggs.andutils.extension.*
import com.jueggs.domain.model.*
import com.jueggs.domain.usecase.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.util.*
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

    private val _onToast: MediatorLiveData<Int> = MediatorLiveData()
    val onToast: LiveData<Int>
        get() = _onToast

    private val _onLongToast: MediatorLiveData<Int> = MediatorLiveData()
    val onLongToast: LiveData<Int>
        get() = _onLongToast

    private val _onHideKeyboard: MediatorLiveData<Unit> = MediatorLiveData()
    val onHideKeyboard: LiveData<Unit>
        get() = _onHideKeyboard

    private val _onShowProgress: MediatorLiveData<Boolean> = MediatorLiveData()
    val onShowProgress: LiveData<Boolean>
        get() = _onShowProgress

    init {
        _onLongToast.addSource(criteriaViewModel.onLongToast) { _onLongToast.fireId(it!!) }
        _onLongToast.addSource(resultViewModel.onLongToast) { _onLongToast.fireId(it!!) }
        _onHideKeyboard.addSource(criteriaViewModel.onHideKeyboard) { _onHideKeyboard.fire() }
        //TODO lib
        _onShowProgress.addSource(criteriaViewModel.onShowProgress) { _onShowProgress.value = it }
    }

    var isDataDownloaded: Boolean
        get() = getApplication<App>().defaultSharedPreferences.getBoolean(PREFS_DATA_DOWNLOADED, false)
        set(value) = getApplication<App>().defaultSharedPreferences.edit { putBoolean(PREFS_DATA_DOWNLOADED, value) }

    val checkedNavigationItem: MutableLiveData<Int> = MutableLiveData()

    fun onInitialStart() = doWithNetworkConnection<App> {
        initialStartUseCase.execute()

        //TODO for developing reasons, remove later
        criteriaViewModel.tag.value = "java"
        criteriaViewModel.fromDate.set(DateTime().minusWeeks(1).toDate())
        criteriaViewModel.toDate.set(Date())
    }

    fun onDownload() = doWithNetworkConnection<App> {
        _onShowProgress.fireTrue()
        downloadUseCase.execute().deferredResult
                .doOnSuccess {
                    when (it) {
                        Success -> {
                            isDataDownloaded = true
                            _onToast.fireId(R.string.toast_data_downloaded)
                        }
                        is Failure -> _onLongToast.fireId(R.string.error_download_failed)
                    }
                    _onShowProgress.fireFalse()
                }
    } otherwise { _onLongToast.fireId(R.string.error_no_network) }
}