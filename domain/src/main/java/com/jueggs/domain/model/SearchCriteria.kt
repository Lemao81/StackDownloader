package com.jueggs.domain.model

import java.util.*

class SearchCriteria(
        val orderType: Int,
        val sortType: Int,
        val tags: List<String>?,
        val from: Date?,
        val to: Date?
)