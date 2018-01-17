package com.jueggs.stackdownloader.model

class ItemShell<T>(
        val items: List<T>,
        val has_more: Boolean,
        val quota_max: Int,
        val quota_remaining: Int)