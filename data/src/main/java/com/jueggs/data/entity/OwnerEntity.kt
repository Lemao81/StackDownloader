package com.jueggs.data.entity

import android.arch.persistence.room.*

@Entity(tableName = "Owner")
class OwnerEntity(
        @PrimaryKey(autoGenerate = true) var id: Long,
        var reputation: Int?,
        var profileImage: String?,
        var name: String?
) {
    @Ignore
    constructor() : this(0, 0, "", "")
}