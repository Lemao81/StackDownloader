package com.jueggs.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class QuestionOwnerTagJoin(
        @Embedded
        var question: QuestionEntity,
        @Embedded
        var owner: OwnerEntity,
        @Relation(parentColumn = "id", entityColumn = "questionId")
        var tags: List<QuestionTagJoinEntity>
) {
    constructor() : this(QuestionEntity(), OwnerEntity(), emptyList())
}