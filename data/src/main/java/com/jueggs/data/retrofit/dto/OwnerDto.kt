package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class OwnerDto(
        @SerializedName("reputation")
        val reputation: Int? = null,
        @SerializedName("user_id")
        val userId: Long? = null,
        @SerializedName("profile_image")
        val profileImage: String? = null,
        @SerializedName("display_name")
        val displayName: String? = null)