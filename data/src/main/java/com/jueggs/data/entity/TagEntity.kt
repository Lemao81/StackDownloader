package com.jueggs.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Tag")
class TagEntity(
    @PrimaryKey
    var name: String,
    var count: Int?
) {
    @Ignore
    constructor() : this("", 0)
}