package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

abstract class ContentElementDto(
        @SerializedName("question_id")
        var questionId: Long? = null,
        @SerializedName("owner")
        val owner: OwnerDto? = null,
        @SerializedName("score")
        val score: Int? = null,
        @SerializedName("creation_date")
        val creationDate: Long? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("body")
        var body: String? = null,
        @SerializedName("body_markdown ")
        var bodyMarkdown: String? = null)