package com.jueggs.data.db.entity

import android.arch.persistence.room.*

@Entity
class OwnerEntity(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var reputation: Int = 0,
        var profileImage: String = "",
        var name: String = ""
        ) {
}