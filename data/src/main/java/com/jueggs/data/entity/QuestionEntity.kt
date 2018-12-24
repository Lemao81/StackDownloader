package com.jueggs.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Question")
class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var ownerId: Long?,
    var isAnswered: Boolean?,
    var viewCount: Int?,
    var answerCount: Int?,
    var score: Int?,
    var creationDateTime: String?,
    var title: String?,
    var body: String?,
    var bodyMarkdown: String?
) {
    @Ignore
    constructor() : this(0, 0, false, 0, 0, 0, DateTime().toString(), "", "", "")
}