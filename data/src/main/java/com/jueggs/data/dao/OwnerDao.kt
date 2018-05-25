package com.jueggs.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.OwnerEntity

@Dao
interface OwnerDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(owner: List<OwnerEntity>)

    @Query("SELECT * FROM owner")
    fun getAll(): List<OwnerEntity>
}