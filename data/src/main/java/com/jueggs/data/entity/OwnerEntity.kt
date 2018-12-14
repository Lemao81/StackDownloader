package com.jueggs.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Owner")
class OwnerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "owner_id")
    var id: Long?,
    var reputation: Int?,
    var profileImage: String?,
    var name: String?
) {
    @Ignore
    constructor() : this(0, 0, "", "")
}