package com.jueggs.stackdownloader.model

class Question(
        val tags: List<String>,
        val isAnswered: Boolean,
        val viewCount: Int,
        val answerCount: Int,
        var tagsLabel: String,
        var answerCountLabel: String,
        questionId: Long,
        owner: Owner,
        score: Int,
        lastActivityDate: Long,
        lastEditDate: Long,
        creationDate: Long,
        link: String,
        title: String,
        body: String,
        bodyMarkdown: String,
        scoreLabel: String,
        creationLabel: String,
        bodyFromHtml: Any) : ContentElement(questionId, owner, score, lastActivityDate, lastEditDate, creationDate, link, title, body, bodyMarkdown, scoreLabel, creationLabel, bodyFromHtml)