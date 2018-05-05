package com.jueggs.stackdownloader.retrofit.dto

class ItemShellData<T>(
        val items: List<T>? = null,
        val has_more: Boolean? = null,
        val quota_max: Int? = null,
        val quota_remaining: Int? = null)