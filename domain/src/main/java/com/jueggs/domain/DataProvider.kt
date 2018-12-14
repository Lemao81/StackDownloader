package com.jueggs.domain

import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.domain.model.Tag
import io.reactivex.Single

interface DataProvider {
    fun fetchTags(): Single<List<Tag>>

    fun fetchQuestions(searchCriteria: SearchCriteria): Single<List<Question>>

    fun fetchAnswers(questionIds: List<Long>): Single<List<Answer>>
}