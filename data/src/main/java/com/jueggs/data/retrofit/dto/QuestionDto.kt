package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class QuestionDto(
        @SerializedName("tags")
        val tags: List<String>? = null,
        @SerializedName("is_answered")
        val isAnswered: Boolean? = null,
        @SerializedName("view_count")
        val viewCount: Int? = null,
        @SerializedName("answer_count")
        val answerCount: Int? = null) : ContentElementDto()