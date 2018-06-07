package com.jueggs.data.entity

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import java.util.*

@Entity(tableName = "Answer", foreignKeys = [(ForeignKey(entity = QuestionEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("questionId"), onDelete = CASCADE))])
class AnswerEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var questionId: Long,
        var ownerId: Long,
        var isAccepted: Boolean?,
        var score: Int?,
        var creationDate: Date?,
        var title: String?,
        var body: String?,
        var bodyMarkdown: String?
) {
    @Ignore
    constructor() : this(0, 0, 0, false, 0, Date(), "", "", "")
}