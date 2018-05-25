package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.TagEntity

@Dao
interface TagDao {
    @Query("SELECT * FROM tag")
    fun getAllLive(): LiveData<List<TagEntity>>

    @Query("SELECT * FROM tag")
    fun getAll(): List<TagEntity>

    @Insert(onConflict = REPLACE)
    fun insertAll(tags: List<TagEntity>)
}