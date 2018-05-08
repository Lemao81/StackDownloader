package com.jueggs.data.retrofit.dto

class ItemShellDto<T>(
        val items: List<T>? = null,
        val has_more: Boolean? = null,
        val quota_max: Int? = null,
        val quota_remaining: Int? = null)