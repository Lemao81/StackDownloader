package com.jueggs.stackdownloader.models

import android.text.Spanned
import com.jueggs.utils.EMPTY_STRING
import com.jueggs.utils.INVALID_VALUE
import com.jueggs.utils.INVALID_VALUE_L

abstract class ContentElement(
        var question_id: Long,
        val owner: Owner?,
        val score: Int,
        val last_activity_date: Long,
        var last_edit_date: Long,
        val creation_date: Long,
        val link: String,
        val title: String,
        var body: String,
        var bodyFromHtml: Spanned?,
        var body_markdown: String,
        var creationLabel: String?,
        var scoreLabel: String?) {
    constructor() : this(INVALID_VALUE_L, null, INVALID_VALUE, INVALID_VALUE_L, INVALID_VALUE_L, INVALID_VALUE_L, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, null, EMPTY_STRING, null, null)
}