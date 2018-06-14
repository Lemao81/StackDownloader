package com.jueggs.domain.model

sealed class UseCaseRequest

data class AddTagRequest(val tag: CharSequence?, val selectedTags: MutableList<String>?) : UseCaseRequest()

data class ShowQuestionRequest(val question: Question) : UseCaseRequest()