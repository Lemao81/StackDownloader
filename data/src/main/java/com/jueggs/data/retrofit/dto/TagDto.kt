package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class TagDto(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("count")
    var count: Int? = null
)