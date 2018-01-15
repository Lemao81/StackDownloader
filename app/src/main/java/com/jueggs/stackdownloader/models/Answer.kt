package com.jueggs.stackdownloader.models

import com.jueggs.utils.INVALID_VALUE
import com.jueggs.utils.INVALID_VALUE_L

class Answer(
        var down_vote_count: Int,
        var up_vote_count: Int,
        var accepted: Boolean,
        var answer_id: Long) : ContentElement() {
    constructor() : this(INVALID_VALUE, INVALID_VALUE, false, INVALID_VALUE_L)
}