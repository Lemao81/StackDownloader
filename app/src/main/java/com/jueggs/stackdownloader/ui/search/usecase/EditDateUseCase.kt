package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.stackdownloader.ui.search.SearchViewModel

class EditDateUseCase {
    fun from(viewModel: SearchViewModel) {
        viewModel.onEditFromDate.value = Unit
    }

    fun to(viewModel: SearchViewModel) {
        viewModel.onEditToDate.value = Unit
    }
}