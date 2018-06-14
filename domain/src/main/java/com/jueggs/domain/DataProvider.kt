package com.jueggs.domain

import com.jueggs.domain.model.*
import io.reactivex.Single

interface DataProvider {
    fun fetchTags(): Single<List<Tag>>

    fun fetchQuestions(searchCriteria: SearchCriteria): Single<List<Question>>

    fun fetchAnswers(questionIds: List<Long>): Single<List<Answer>>
}