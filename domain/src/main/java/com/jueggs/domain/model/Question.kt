package com.jueggs.domain.model

class Question(
    var id: Long = 0,
    var tags: List<String> = emptyList(),
    var isAnswered: Boolean? = false,
    var viewCount: Int? = null,
    var answerCount: Int? = null
) : ListItem()