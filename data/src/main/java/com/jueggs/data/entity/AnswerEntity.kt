package com.jueggs.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

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