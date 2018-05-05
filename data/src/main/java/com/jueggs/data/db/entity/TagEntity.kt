package com.jueggs.data.db.entity

import android.arch.persistence.room.*

@Entity
class TagEntity(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var name: String = ""
) {
}