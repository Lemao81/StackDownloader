package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import androidx.core.content.edit
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.domain.model.*
import com.jueggs.domain.usecase.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.util.isDebug
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
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
        _onLongToast.addSource(criteriaViewModel.onLongToast) { _onLongToast.fire(it!!) }
        _onLongToast.addSource(resultViewModel.onLongToast) { _onLongToast.fire(it!!) }
        _onHideKeyboard.addSource(criteriaViewModel.onHideKeyboard) { _onHideKeyboard.fire() }
        //TODO lib
        _onShowProgress.addSource(criteriaViewModel.onShowProgress) { _onShowProgress.value = it }
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

    fun onDownload() = doWithNetworkConnection<App> {
        _onShowProgress.fireTrue()

        launch(UI) {
            val result = withContext(CommonPool) {
                downloadUseCase.execute().deferredResult.await()
            }

            when (result) {
                Success -> {
                    isDataDownloaded = true
                    _onToast.fire(R.string.toast_data_downloaded)
                }
                is Failure -> {
                    _onLongToast.fire(R.string.error_download_failed)
                    if (AppMode.isDebug)
                        throw result.exception
                }
            }
            _onShowProgress.fireFalse()
        }
    } otherwise { _onLongToast.fire(R.string.error_no_network) }
}