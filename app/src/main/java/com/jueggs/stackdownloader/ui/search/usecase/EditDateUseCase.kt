package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.stackdownloader.fire
import com.jueggs.stackdownloader.ui.search.SearchViewModel

class EditDateUseCase {
    fun from(viewModel: SearchViewModel) = viewModel.onEditFromDate.fire()

    fun to(viewModel: SearchViewModel) = viewModel.onEditToDate.fire()
}