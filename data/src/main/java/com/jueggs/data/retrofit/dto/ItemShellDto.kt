package com.jueggs.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class ItemShellDto<T>(
    @SerializedName("items")
    var items: List<T>? = null,
    @SerializedName("has_more")
    var hasMore: Boolean? = null,
    @SerializedName("quota_max")
    var quotaMax: Int? = null,
    @SerializedName("quota_remaining")
    var quotaRemaining: Int? = null
)