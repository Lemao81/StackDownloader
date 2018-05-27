package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.AnswerEntity

@Dao
interface AnswerDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(answers: List<AnswerEntity>)

    @Query("SELECT * FROM answer")
    fun getAll(): List<AnswerEntity>

    @Query("SELECT * FROM answer WHERE questionId = :questionId")
    fun getAnswersOfQuestion(questionId: Long): List<AnswerEntity>

    @Query("SELECT * FROM answer WHERE questionId = :questionId")
    fun getAnswersOfQuestionLive(questionId: Long): LiveData<List<AnswerEntity>>

    @Query("DELETE FROM answer")
    fun deleteAll()
}