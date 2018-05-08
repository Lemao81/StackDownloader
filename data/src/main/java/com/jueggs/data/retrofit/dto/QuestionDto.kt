package com.jueggs.data.retrofit.dto

class QuestionDto(
        val tags: List<String>? = null,
        val is_answered: Boolean? = null,
        val view_count: Int? = null,
        val answer_count: Int? = null) : ContentElementDto()