package com.jueggs.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.QuestionTagJoinEntity

@Dao
interface QuestionTagJoinDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(questionTagJoins: List<QuestionTagJoinEntity>)

    @Query("SELECT * FROM question_tag")
    fun getAll(): List<QuestionTagJoinEntity>
}