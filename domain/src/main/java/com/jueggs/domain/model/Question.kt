package com.jueggs.domain.model

import java.util.*

class Question(
        var id: Long = 0,
        var ownerId: Long = 0,
        var isAnswered: Boolean = false,
        var viewCount: Int = 0,
        var answerCount: Int = 0,
        var score: Int = 0,
        var creationDate: Date = Date(0),
        var title: String = "",
        var body: String = ""
)