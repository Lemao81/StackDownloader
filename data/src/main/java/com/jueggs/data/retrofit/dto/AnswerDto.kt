package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class AnswerDto(
        @SerializedName("is_accepted")
        var isAccepted: Boolean? = null,
        @SerializedName("answer_id")
        var answerId: Long? = null
) : ContentElementDto()