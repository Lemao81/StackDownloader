package com.jueggs.domain.model

class ItemShell<T>(
    val items: List<T>? = null,
    val hasMore: Boolean? = null,
    val quotaMax: Int? = null,
    val quotaRemaining: Int? = null
)