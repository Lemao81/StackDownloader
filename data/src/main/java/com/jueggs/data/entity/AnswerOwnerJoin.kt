package com.jueggs.data.entity

import android.arch.persistence.room.Embedded

class AnswerOwnerJoin(
        @Embedded
        var answer: AnswerEntity,
        @Embedded
        var owner: OwnerEntity
) {
    constructor() : this(AnswerEntity(), OwnerEntity())
}