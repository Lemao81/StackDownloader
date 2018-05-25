package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.jueggs.data.entity.AnswerEntity

@Dao
interface AnswerDao {
    @Query("SELECT * FROM answer WHERE questionId = :questionId")
    fun getAnswersOfQuestionLive(questionId: Long): LiveData<List<AnswerEntity>>

    @Query("SELECT * FROM answer WHERE questionId = :questionId")
    fun getAnswersOfQuestion(questionId: Long): List<AnswerEntity>
}