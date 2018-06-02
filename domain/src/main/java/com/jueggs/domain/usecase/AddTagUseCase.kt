package com.jueggs.domain.usecase

import com.jueggs.domain.Repository
import com.jueggs.domain.model.*

class AddTagUseCase(private val repository: Repository) : UseCase<AddTagRequest, AddTagResult> {

    override fun execute(req: AddTagRequest?): AddTagResult {
        val request = req ?: throw IllegalStateException("Request must not be null")

        try {
            val tag = request.tag?.toString() ?: return EmptyInput
            val selectedTags = request.selectedTags ?: mutableListOf()

            when {
                selectedTags.contains(tag) -> return TagAlreadyAdded
                !repository.getAllTags().map { it.name }.filterNotNull().contains(tag) -> return TagNotAvailable
            }

            return TagAdded(selectedTags.apply { add(tag) })
        } catch (exception: Exception) {
            return AddTagFailure(exception)
        }
    }
}