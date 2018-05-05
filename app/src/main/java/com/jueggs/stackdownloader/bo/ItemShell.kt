package com.jueggs.stackdownloader.bo

class ItemShell<T>(
        val items: List<T>,
        val hasMore: Boolean,
        val quotaMax: Int,
        val quotaRemaining: Int)