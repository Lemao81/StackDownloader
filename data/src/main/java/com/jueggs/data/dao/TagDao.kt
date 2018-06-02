package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.TagEntity

@Dao
interface TagDao {
    @Query("SELECT * FROM tag")
    fun getAll(): List<TagEntity>

    @Query("SELECT name FROM tag")
    fun getAllNames(): List<String>

    @Query("SELECT name FROM tag")
    fun getAllNamesLive(): LiveData<List<String>>

    @Insert(onConflict = REPLACE)
    fun insertAll(tags: List<TagEntity>)
}