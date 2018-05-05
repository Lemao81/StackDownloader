package com.jueggs.data.db.entity

import android.arch.persistence.room.*
import java.util.*

@Entity
class QuestionEntity(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var ownerId: Long = 0,
        var isAnswered: Boolean = false,
        var viewCount: Int = 0,
        var answerCount: Int = 0,
        var score: Int = 0,
        var creationDate: Date = Date(0),
        var title: String = "",
        var body: String = ""
) {
}