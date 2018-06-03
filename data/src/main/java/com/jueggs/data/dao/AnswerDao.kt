package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.*

@Dao
abstract class AnswerDao : BaseDao<AnswerEntity> {
    @Insert(onConflict = REPLACE)
    abstract fun insertAll(answers: List<AnswerEntity>)

    @Transaction
    open fun replaceAll(answers: List<AnswerEntity>) {
        deleteAll()
        insertAll(answers)
    }

    @Query("SELECT * FROM answer")
    abstract fun getAll(): List<AnswerEntity>

    @Query("SELECT * FROM answer INNER JOIN owner ON answer.ownerId = owner.owner_id WHERE questionId = :questionId")
    abstract fun getAnswersOfQuestionIncludingOwner(questionId: Long): List<AnswerOwnerJoin>

    @Query("SELECT * FROM answer INNER JOIN owner ON answer.ownerId = owner.owner_id WHERE questionId = :questionId")
    abstract fun getAnswersOfQuestionIncludingOwnerLive(questionId: Long): LiveData<List<AnswerOwnerJoin>>

    @Query("DELETE FROM answer")
    abstract fun deleteAll()
}