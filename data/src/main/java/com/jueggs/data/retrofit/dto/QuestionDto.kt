package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class QuestionDto(
    @SerializedName("question_id")
    var id: Long? = null,
    @SerializedName("owner")
    var owner: OwnerDto? = null,
    @SerializedName("tags")
    var tags: List<String>? = null,
    @SerializedName("is_answered")
    var isAnswered: Boolean? = null,
    @SerializedName("view_count")
    var viewCount: Int? = null,
    @SerializedName("answer_count")
    var answerCount: Int? = null,
    @SerializedName("score")
    var score: Int? = null,
    @SerializedName("creation_date")
    var creationDate: Long? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null,
    @SerializedName("body_markdown")
    var bodyMarkdown: String? = null
)