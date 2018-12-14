package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.domain.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTagUseCase(private val repository: Repository) : UseCase<AddTagRequest>() {

    override fun doExecute(request: AddTagRequest) {
        val tag = request.tag?.toString() ?: ""
        val selectedTags = request.selectedTags ?: mutableListOf()

        // TODO remove globalscope
        GlobalScope.launch {
            try {
                val result = when {
                    tag.isBlank() -> EmptyInput
                    selectedTags.contains(tag) -> TagAlreadyAdded
                    !repository.getAllTagNames().contains(tag) -> TagNotAvailable
                    else -> TagAdded(selectedTags.apply { add(tag) })
                }
                data.postValue(result)
            } catch (exception: Exception) {
                data.postValue(Error(exception))
            }
        }
    }
}

data class AddTagRequest(val tag: CharSequence?, val selectedTags: MutableList<String>?)