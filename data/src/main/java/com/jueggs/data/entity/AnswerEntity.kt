package com.jueggs.data.entity

import android.arch.persistence.room.*
import java.util.*

@Entity
class AnswerEntity(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var ownerId: Long = 0,
        var questionId: Long = 0,
        var isAccepted: Boolean = false,
        var score: Int = 0,
        var creationDate: Date = Date(0),
        var title: String = "",
        var body: String = ""
)