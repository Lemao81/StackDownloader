package com.jueggs.data

import com.jueggs.data.entity.TagEntity
import io.reactivex.Single

interface StackRepository {
    fun getTags(): Single<List<TagEntity>>
}