package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.andutils.interfaces.BaseDao
import com.jueggs.data.entity.TagEntity

@Dao
abstract class TagDao : BaseDao<TagEntity> {
    @Query("SELECT * FROM tag")
    abstract fun getAll(): List<TagEntity>

    @Query("SELECT name FROM tag")
    abstract fun getAllNames(): List<String>

    @Query("SELECT name FROM tag")
    protected abstract fun getAllNamesLive(): LiveData<List<String>>

    fun getAllNamesLiveDistinct(): LiveData<List<String>> = getAllNamesLive().distinctUntilChanged()

    @Insert(onConflict = REPLACE)
    abstract fun insertAll(tags: List<TagEntity>)
}