package com.jueggs.stackdownloader.retrofit.dto

abstract class ContentElementData(
        var question_id: Long? = null,
        val owner: OwnerData? = null,
        val score: Int? = null,
        val creation_date: Long? = null,
        val title: String? = null,
        var body: String? = null,
        var body_markdown: String? = null)