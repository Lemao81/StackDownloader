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

    @Query("SELECT * FROM question INNER JOIN owner ON question.ownerId = owner.owner_id")
    fun getAllIncludingOwnerAndTagsLive(): LiveData<List<QuestionOwnerTagJoin>>

    @Query("SELECT * FROM question INNER JOIN owner ON question.ownerId = owner.owner_id")
    fun getAllIncludingOwnerAndTags(): List<QuestionOwnerTagJoin>

    @Query("DELETE FROM question")
    fun deleteAll()
}