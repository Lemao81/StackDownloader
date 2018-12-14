package com.jueggs.domain.model

import org.joda.time.LocalDate

class SearchCriteria(
    val orderType: Int?,
    val sortType: Int?,
    val tags: List<String>?,
    val from: LocalDate?,
    val to: LocalDate?
)