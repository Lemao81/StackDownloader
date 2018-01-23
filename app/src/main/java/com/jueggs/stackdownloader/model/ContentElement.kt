package com.jueggs.stackdownloader.model

abstract class ContentElement(
        var questionId: Long,
        val owner: Owner,
        val score: Int,
        val lastActivityDate: Long,
        var lastEditDate: Long,
        val creationDate: Long,
        val link: String,
        val title: String,
        var body: String,
        var bodyMarkdown: String,
        var scoreLabel: String,
        var creationLabel: String,
        var bodyFromHtml: Any)