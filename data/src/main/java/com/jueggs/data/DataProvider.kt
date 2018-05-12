package com.jueggs.data

import com.jueggs.domain.model.*
import io.reactivex.Single

interface DataProvider {
    suspend fun fetchQuestions(searchCriteria: SearchCriteria): Single<List<Question>>

    suspend fun fetchAnswers(questionIds: List<Long>): Single<List<Answer>>
}