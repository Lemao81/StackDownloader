package com.jueggs.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.jueggs.andutils.interfaces.BaseDao
import com.jueggs.data.entity.QuestionEntity
import com.jueggs.data.entity.QuestionOwnerTagJoin

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
    protected abstract fun getAllIncludingOwnerAndTagsLive(): LiveData<List<QuestionOwnerTagJoin>>

    fun getAllIncludingOwnerAndTagsLiveDistinct(): LiveData<List<QuestionOwnerTagJoin>> = getAllIncludingOwnerAndTagsLive().distinctUntilChanged()

    @Query("DELETE FROM question")
    abstract fun deleteAll()

    @Transaction
    open fun replaceAll(questions: List<QuestionEntity>) {
        deleteAll()
        insertAll(questions)
    }
}

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> {
    val distinctLiveData = MediatorLiveData<T>()

    distinctLiveData.addSource(this, object : Observer<T> {
        private var initialized = false
        private var lastObj: T? = null

        override fun onChanged(obj: T?) {
            if (!initialized) {
                initialized = true
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            } else if ((obj == null && lastObj != null) || obj != lastObj) {
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            }
        }
    })

    return distinctLiveData
}