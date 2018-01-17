package com.jueggs.stackdownloader.model

class SearchCriteria(
        val pageSize: String,
        val sortOrder: String,
        val orderType: String,
        val minScore: String,
        val tags: List<String>)