package com.jueggs.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.jueggs.andutils.interfaces.BaseDao
import com.jueggs.data.entity.OwnerEntity

@Dao
abstract class OwnerDao : BaseDao<OwnerEntity> {
    @Insert(onConflict = REPLACE)
    abstract fun insertAll(owner: List<OwnerEntity>)

    @Transaction
    open fun replaceAll(owner: List<OwnerEntity>) {
        deleteAll()
        insertAll(owner)
    }

    @Query("SELECT * FROM owner")
    abstract fun getAll(): List<OwnerEntity>

    @Query("DELETE FROM owner")
    abstract fun deleteAll()
}