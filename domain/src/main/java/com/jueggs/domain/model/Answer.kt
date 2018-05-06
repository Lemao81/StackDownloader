package com.jueggs.domain.model

import java.util.*

class Answer(
        var id: Long = 0,
        var ownerId: Long = 0,
        var questionId: Long = 0,
        var isAccepted: Boolean = false,
        var score: Int = 0,
        var creationDate: Date = Date(0),
        var title: String = "",
        var body: String = ""
)