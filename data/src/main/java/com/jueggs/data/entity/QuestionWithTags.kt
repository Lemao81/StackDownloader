package com.jueggs.data.entity

import android.arch.persistence.room.*

class QuestionWithTags(
        @Embedded var question: QuestionEntity,
        @Relation(parentColumn = "id", entityColumn = "questionId") var tags: List<QuestionTagJoinEntity>
) {
    constructor() : this(QuestionEntity(), emptyList())
}