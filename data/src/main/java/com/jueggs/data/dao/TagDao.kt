package com.jueggs.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.TagEntity

@Dao
interface TagDao {
    @Query("SELECT * FROM Tag")
    fun getAllTags(): LiveData<List<TagEntity>>

    @Insert(onConflict = REPLACE)
    fun insertTags(tags: List<TagEntity>)
}