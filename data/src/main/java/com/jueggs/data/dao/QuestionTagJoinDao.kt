package com.jueggs.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.QuestionTagJoinEntity

@Dao
abstract class QuestionTagJoinDao : BaseDao<QuestionTagJoinEntity> {
    @Insert(onConflict = REPLACE)
    abstract fun insertAll(questionTagJoins: List<QuestionTagJoinEntity>)

    @Transaction
    open fun replaceAll(questionTagJoins: List<QuestionTagJoinEntity>) {
        deleteAll()
        insertAll(questionTagJoins)
    }

    @Query("SELECT * FROM question_tag")
    abstract fun getAll(): List<QuestionTagJoinEntity>

    @Query("DELETE FROM question_tag")
    abstract fun deleteAll()
}