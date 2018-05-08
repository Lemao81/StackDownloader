package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class ItemShellDto<T>(
        @SerializedName("items")
        val items: List<T>? = null,
        @SerializedName("has_more")
        val hasMore: Boolean? = null,
        @SerializedName("quota_max")
        val quotaMax: Int? = null,
        @SerializedName("quota_remaining")
        val quotaRemaining: Int? = null)