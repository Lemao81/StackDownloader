package com.jueggs.stackdownloader.models

import com.jueggs.utils.INVALID_VALUE


class ItemShell<T>(val items: List<T>, val has_more: Boolean, val quota_max: Int, val quota_remaining: Int) {
    constructor() : this(arrayListOf(), false, INVALID_VALUE, INVALID_VALUE)
}