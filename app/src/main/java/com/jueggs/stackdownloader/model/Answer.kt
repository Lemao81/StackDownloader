package com.jueggs.stackdownloader.model

class Answer(
        var downVoteCount: Int,
        var upVoteCount: Int,
        var accepted: Boolean,
        var answerId: Long,
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