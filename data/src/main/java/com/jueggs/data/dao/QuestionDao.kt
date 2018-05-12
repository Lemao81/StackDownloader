package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.QuestionEntity

@Dao
interface QuestionDao {
    @Insert(onConflict = REPLACE)
    fun insertQuestions(questions: List<QuestionEntity>)

    @Query("SELECT * FROM Question")
    fun getAllQuestions(): LiveData<List<QuestionEntity>>
}