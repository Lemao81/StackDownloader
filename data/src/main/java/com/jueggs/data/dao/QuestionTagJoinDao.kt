package com.jueggs.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.jueggs.andutils.interfaces.BaseDao
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