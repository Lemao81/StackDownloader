package com.jueggs.stackdownloader.model

abstract class ContentElementData(
        var question_id: Long? = null,
        val owner: Owner? = null,
        val score: Int? = null,
        val last_activity_date: Long? = null,
        var last_edit_date: Long? = null,
        val creation_date: Long? = null,
        val link: String? = null,
        val title: String? = null,
        var body: String? = null,
        var body_markdown: String? = null)