package com.jueggs.data.entity

import android.arch.persistence.room.*

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