package com.jueggs.stackdownloader.model

import android.text.Spanned

class Question(
        val tags: List<String>,
        val is_answered: Boolean,
        val view_count: Int,
        val answer_count: Int,
        var tagsLabel: String,
        var answerCountLabel: String,
        question_id: Long,
        owner: Owner,
        score: Int,
        last_activity_date: Long,
        last_edit_date: Long,
        creation_date: Long,
        link: String,
        title: String,
        body: String,
        body_markdown: String,
        scoreLabel: String,
        creationLabel: String,
        bodyFromHtml: Any) : ContentElement(question_id, owner, score, last_activity_date, last_edit_date, creation_date, link, title, body, body_markdown, scoreLabel, creationLabel, bodyFromHtml)