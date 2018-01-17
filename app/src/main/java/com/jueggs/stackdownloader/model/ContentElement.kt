package com.jueggs.stackdownloader.model

import android.text.Spanned

abstract class ContentElement(
        var question_id: Long,
        val owner: Owner,
        val score: Int,
        val last_activity_date: Long,
        var last_edit_date: Long,
        val creation_date: Long,
        val link: String,
        val title: String,
        var body: String,
        var body_markdown: String,
        var scoreLabel: String,
        var creationLabel: String,
        var bodyFromHtml: Any)