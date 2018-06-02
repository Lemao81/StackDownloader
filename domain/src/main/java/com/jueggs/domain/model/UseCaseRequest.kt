package com.jueggs.domain.model

sealed class UseCaseRequest

data class AddTagRequest(val tag: String) : UseCaseRequest()