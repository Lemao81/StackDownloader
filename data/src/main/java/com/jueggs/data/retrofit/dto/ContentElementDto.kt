package com.jueggs.data.retrofit.dto

abstract class ContentElementDto(
        var question_id: Long? = null,
        val owner: OwnerDto? = null,
        val score: Int? = null,
        val creation_date: Long? = null,
        val title: String? = null,
        var body: String? = null,
        var body_markdown: String? = null)