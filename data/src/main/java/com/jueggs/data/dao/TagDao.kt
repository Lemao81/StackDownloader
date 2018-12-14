package com.jueggs.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
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