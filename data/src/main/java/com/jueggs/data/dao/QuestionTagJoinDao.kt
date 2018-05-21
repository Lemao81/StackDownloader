package com.jueggs.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.*

@Dao
interface QuestionTagJoinDao {
    @Insert(onConflict = REPLACE)
    fun insertQuestionTagJoin(questionTagJoin: QuestionTagJoinEntity)

    @Query("SELECT * FROM tag INNER JOIN question_tag ON tag.name=question_tag.tagName WHERE question_tag.questionId=:questionId")
    fun getTagsOfQuestion(questionId: Long): List<TagEntity>
}