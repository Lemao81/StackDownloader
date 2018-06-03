package com.jueggs.domain.usecase

import com.jueggs.domain.Repository
import com.jueggs.domain.model.*
import kotlinx.coroutines.experimental.async

class AddTagUseCase(private val repository: Repository) : UseCase<AddTagRequest, AddTagResult> {

    override fun execute(req: AddTagRequest?): AddTagResult {
        val request = req ?: throw IllegalStateException("Request must not be null")
        val tag = request.tag?.toString() ?: ""
        val selectedTags = request.selectedTags ?: mutableListOf()

        val deferred = async {
            try {
                when {
                    tag.isBlank() -> EmptyInput
                    selectedTags.contains(tag) -> TagAlreadyAdded
                    !repository.getAllTagNames().contains(tag) -> TagNotAvailable
                    else -> TagAdded(selectedTags.apply { add(tag) })
                }
            } catch (exception: Exception) {
                AddTagFailure(exception)
            }
        }

        return AddTagResult(deferred)
    }
}