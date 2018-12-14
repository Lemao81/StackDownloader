package com.jueggs.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "Question_Tag", primaryKeys = ["questionId", "tagName"],
    foreignKeys = [
        (ForeignKey(entity = QuestionEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("questionId"), onUpdate = CASCADE, onDelete = CASCADE)),
        (ForeignKey(entity = TagEntity::class, parentColumns = arrayOf("name"), childColumns = arrayOf("tagName"), onUpdate = CASCADE, onDelete = CASCADE))])
class QuestionTagJoinEntity(
    var questionId: Long,
    var tagName: String
)