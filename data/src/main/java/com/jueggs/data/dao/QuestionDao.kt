package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.*

@Dao
abstract class QuestionDao : BaseDao<QuestionEntity> {
    @Insert(onConflict = REPLACE)
    abstract fun insertAll(questions: List<QuestionEntity>)

    @Query("SELECT id FROM question")
    abstract fun getAllIds(): List<Long>

    @Query("SELECT * FROM question")
    abstract fun getAll(): List<QuestionEntity>

    @Query("SELECT * FROM question")
    abstract fun getAllLive(): LiveData<List<QuestionEntity>>

    @Transaction
    @Query("SELECT * FROM question INNER JOIN owner ON question.ownerId = owner.owner_id")
    abstract fun getAllIncludingOwnerAndTags(): List<QuestionOwnerTagJoin>

    @Transaction
    @Query("SELECT * FROM question INNER JOIN owner ON question.ownerId = owner.owner_id")
    abstract fun getAllIncludingOwnerAndTagsLive(): LiveData<List<QuestionOwnerTagJoin>>

    @Query("DELETE FROM question")
    abstract fun deleteAll()

    @Transaction
    open fun replaceAll(questions: List<QuestionEntity>) {
        deleteAll()
        insertAll(questions)
    }
}

//TODO lib
@Dao
interface BaseDao<T> {
    @Insert(onConflict = REPLACE)
    fun insert(vararg element: T)

    @Update(onConflict = REPLACE)
    fun update(vararg element: T)

    @Delete
    fun delete(vararg element: T)
}