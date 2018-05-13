package com.jueggs.data.entity

import android.arch.persistence.room.*

@Entity(tableName = "Tag")
class TagEntity(
        @PrimaryKey(autoGenerate = true) var id: Long,
        var name: String?,
        var count: Int?
) {
    @Ignore
    constructor() : this(0, "", 0)
}