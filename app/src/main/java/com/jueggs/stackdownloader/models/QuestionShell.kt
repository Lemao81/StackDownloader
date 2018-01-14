package com.jueggs.stackdownloader.models

import com.jueggs.stackdownloader.utils.INVALID_VALUE

class QuestionShell(val items: List<Question>, val has_more: Boolean, val quota_max: Int, val quota_remaining: Int) {
    constructor() : this(arrayListOf(), false, INVALID_VALUE, INVALID_VALUE)
}