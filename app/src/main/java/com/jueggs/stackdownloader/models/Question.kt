package com.jueggs.stackdownloader.models

import com.jueggs.utils.INVALID_VALUE
import com.jueggs.utils.INVALID_VALUE_L

class Question(
        val tags: List<String>,
        var tagsLabel: String?,
        val is_answered: Boolean,
        val view_count: Int,
        val answer_count: Int,
        var answerCountLabel: String?) : ContentElement() {
    constructor() : this(arrayListOf(), null, false, INVALID_VALUE, INVALID_VALUE, null)
}