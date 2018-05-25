package com.jueggs.stackdownloader.ui.search.usecase

import androidx.core.content.edit
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import org.jetbrains.anko.defaultSharedPreferences

class DownloadUseCase {
    fun go(viewModel: SearchViewModel) {
        if (viewModel.app.isNetworkConnected()) {
            viewModel.app.defaultSharedPreferences.edit { putBoolean(PREFS_DATA_DOWNLOADED, true) }
        } else
            viewModel.errors.value = R.string.error_no_network
    }
}