package com.jueggs.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jueggs.data.entity.OwnerEntity

@Dao
interface OwnerDao {
    @Insert(onConflict = REPLACE)
    fun insertOwner(owner: OwnerEntity)
}