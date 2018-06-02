package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import androidx.core.content.edit
import com.jueggs.andutils.extension.*
import com.jueggs.domain.model.*
import com.jueggs.domain.usecase.DownloadUseCase
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.usecase.InitialStartUseCase
import com.jueggs.stackdownloader.util.*
import org.jetbrains.anko.defaultSharedPreferences

class SearchViewModel(
        application: Application,
        val searchCriteriaViewModel: SearchCriteriaViewModel,
        val searchResultViewModel: SearchResultViewModel,
        private val downloadUseCase: DownloadUseCase,
        private val initialStartUseCase: InitialStartUseCase
) : AndroidViewModel(application) {

    var isDataDownloaded: Boolean
        get() = getApplication<App>().defaultSharedPreferences.getBoolean(PREFS_DATA_DOWNLOADED, false)
        set(value) = getApplication<App>().defaultSharedPreferences.edit { putBoolean(PREFS_DATA_DOWNLOADED, value) }

    val checkedNavigationItem: MutableLiveData<Int> = MutableLiveData()

    val onError: MutableLiveData<Int> = MutableLiveData()
    val onToast: MutableLiveData<Int> = MutableLiveData()
    val onHideKeyboard: MutableLiveData<Unit> = MutableLiveData()
    val onShowProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun onInitialStart() = initialStartUseCase.go(this)

    fun onDownload() = doWithNetworkConnection<App> {
        onShowProgress.fireTrue()
        downloadUseCase.execute().deferredResult
                .doOnSuccess {
                    when (it) {
                        Success -> {
                            isDataDownloaded = true
                            onToast.fireId(R.string.toast_data_downloaded)
                        }
                        is Failure -> onError.fireId(R.string.error_download_failed)
                    }
                    onShowProgress.fireFalse()
                }
    } otherwise { onError.fireId(R.string.error_no_network) }
}