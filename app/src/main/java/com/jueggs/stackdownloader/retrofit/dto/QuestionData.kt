package com.jueggs.stackdownloader.retrofit.dto

class QuestionData(
        val tags: List<String>? = null,
        val is_answered: Boolean? = null,
        val view_count: Int? = null,
        val answer_count: Int? = null) : ContentElementData()