package com.jueggs.data.entity

import android.arch.persistence.room.*
import java.util.*

@Entity(tableName = "Question")
class QuestionEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var ownerId: Long?,
        var isAnswered: Boolean?,
        var viewCount: Int?,
        var answerCount: Int?,
        var score: Int?,
        var creationDate: Date?,
        var title: String?,
        var body: String?,
        var bodyMarkdown: String?
) {
    @Ignore
    constructor() : this(0, 0, false, 0, 0, 0, Date(), "", "", "")
}