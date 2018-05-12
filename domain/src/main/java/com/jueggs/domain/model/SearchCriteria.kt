package com.jueggs.domain.model

class SearchCriteria(
        val limitTo: String,
        val score: String,
        val orderType: Int,
        val sortType: Int,
        val tags: List<String>?
)