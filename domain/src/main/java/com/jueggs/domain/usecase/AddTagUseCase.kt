package com.jueggs.domain.usecase

import com.jueggs.domain.Repository
import com.jueggs.domain.model.*

class AddTagUseCase(
        private val repository: Repository
) : UseCase<AddTagRequest, UseCaseResult> {
    override fun execute(request: AddTagRequest?): UseCaseResult {
        val requestChecked = request ?: throw IllegalStateException("Request must not be null")

        try {
            val availableTags = repository.getAllTags().map { it.name }.filterNotNull()
        } catch (exception: Exception) {

        }
    }

    fun add(viewModel: SearchCriteriaViewModel) {
        viewModel.availableTags.value?.let { availableTags ->
            val tagInput = viewModel.tag.value?.toString() ?: ""

            when {
                viewModel.selectedTags.value?.contains(tagInput) ?: false -> viewModel.onError.value = R.string.error_tag_already_added
                tagInput.isBlank() -> viewModel.onError.value = R.string.error_no_tag
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

    fun remove(viewModel: SearchCriteriaViewModel, tagName: String) {
        viewModel.selectedTags.value = viewModel.selectedTags.value?.apply { remove(tagName) }
    }
}