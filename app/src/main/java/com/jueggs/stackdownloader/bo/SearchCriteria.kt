package com.jueggs.stackdownloader.bo

class SearchCriteria(
        val pageSize: String,
        val sortOrder: String,
        val orderType: String,
        val minScore: String,
        val tags: List<String>)