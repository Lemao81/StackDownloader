package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.SearchViewModel

class DownloadUseCase {
    fun go(viewModel: SearchViewModel) {
        if (viewModel.getApplication<App>().isNetworkConnected()) {

        } else
            viewModel.errors.value = R.string.error_no_network
    }
}