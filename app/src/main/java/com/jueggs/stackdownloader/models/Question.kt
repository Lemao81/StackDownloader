package com.jueggs.stackdownloader.models

import com.jueggs.stackdownloader.utils.EMPTY_STRING
import com.jueggs.stackdownloader.utils.INVALID_VALUE
import com.jueggs.stackdownloader.utils.INVALID_VALUE_L

class Question(val tags: List<String>, val owner: Owner?, val is_answered: Boolean, val view_count: Int, val answer_count: Int, val score: Int, val last_activity_date: Long,
               val creation_date: Long, val question_id: Long, val link: String, val title: String) {
    constructor() : this(arrayListOf(), null, false, INVALID_VALUE, INVALID_VALUE, INVALID_VALUE, INVALID_VALUE_L, INVALID_VALUE_L, INVALID_VALUE_L, EMPTY_STRING, EMPTY_STRING)
}