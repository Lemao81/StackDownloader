package com.jueggs.domain.model

class Answer(
    var id: Long = 0,
    var questionId: Long? = null,
    var isAccepted: Boolean? = null
) : ListItem()