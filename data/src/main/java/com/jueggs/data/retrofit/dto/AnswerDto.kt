package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class AnswerDto(
    @SerializedName("answer_id")
    var id: Long? = null,
    @SerializedName("question_id")
    var questionId: Long? = null,
    @SerializedName("owner")
    var owner: OwnerDto? = null,
    @SerializedName("is_accepted")
    var isAccepted: Boolean? = null,
    @SerializedName("score")
    var score: Int? = null,
    @SerializedName("creation_date")
    var creationUnixTime: Long? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null,
    @SerializedName("body_markdown")
    var bodyMarkdown: String? = null
)