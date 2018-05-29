package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.*

@Dao
interface AnswerDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(answers: List<AnswerEntity>)

    @Query("SELECT * FROM answer")
    fun getAll(): List<AnswerEntity>

    @Query("SELECT * FROM answer INNER JOIN owner ON answer.ownerId = owner.owner_id WHERE questionId = :questionId")
    fun getAnswersOfQuestionIncludingOwner(questionId: Long): List<AnswerOwnerJoin>

    @Query("SELECT * FROM answer INNER JOIN owner ON answer.ownerId = owner.owner_id WHERE questionId = :questionId")
    fun getAnswersOfQuestionIncludingOwnerLive(questionId: Long): LiveData<List<AnswerOwnerJoin>>

    @Query("DELETE FROM answer")
    fun deleteAll()
}