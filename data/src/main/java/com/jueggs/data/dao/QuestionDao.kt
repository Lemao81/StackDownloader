package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.*

@Dao
interface QuestionDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(questions: List<QuestionEntity>)

    @Query("SELECT * FROM question")
    fun getAllLive(): LiveData<List<QuestionEntity>>

    @Query("SELECT * FROM question")
    fun getAll(): List<QuestionEntity>

    @Query("SELECT * FROM question")
    fun getAllIncludingTagsLive(): LiveData<List<QuestionWithTags>>

    @Query("SELECT * FROM question")
    fun getAllIncludingTags(): List<QuestionWithTags>

    @Query("DELETE FROM question")
    fun deleteAll()
}