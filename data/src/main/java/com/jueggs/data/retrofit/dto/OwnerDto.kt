package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class OwnerDto(
        @SerializedName("user_id")
        var id: Long? = null,
        @SerializedName("reputation")
        var reputation: Int? = null,
        @SerializedName("profile_image")
        var profileImage: String? = null,
        @SerializedName("display_name")
        var name: String? = null)