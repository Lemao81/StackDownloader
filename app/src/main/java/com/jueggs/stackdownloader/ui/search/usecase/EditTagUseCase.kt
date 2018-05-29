package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.andutils.extension.fire
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.SearchViewModel

class EditTagUseCase {
    fun add(viewModel: SearchViewModel) {
        viewModel.availableTags.value?.let { availableTags ->
            val tagInput = viewModel.tag.value?.toString() ?: ""

            when {
                viewModel.selectedTags.value?.contains(tagInput) ?: false -> viewModel.errors.value = R.string.error_tag_already_added
                tagInput.isBlank() -> viewModel.errors.value = R.string.error_no_tag
                availableTags.map { it.name }.contains(tagInput) -> {
                    val tags = viewModel.selectedTags.value ?: mutableListOf()
                    tags.add(tagInput)
                    viewModel.selectedTags.value = tags
                    viewModel.tag.value = ""
                    viewModel.onHideKeyboard.fire()
                }
            }
        }
    }

    fun remove(viewModel: SearchViewModel, tagName: String) {
        viewModel.selectedTags.value = viewModel.selectedTags.value?.apply { remove(tagName) }
    }
}