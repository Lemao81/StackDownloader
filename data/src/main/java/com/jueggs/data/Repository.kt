package com.jueggs.data

import android.arch.lifecycle.LiveData
import com.jueggs.domain.model.*

interface Repository {
    fun addTags(tags: List<Tag>)

    fun addQuestions(questions: List<Question>)

    fun getAllTags(): LiveData<List<Tag>>

    fun getAllQuestions(): LiveData<List<Question>>

    fun getAllQuestionsIncludingTags(): LiveData<List<Question>>

    fun getAnswersOfQuestion(questionId: Long): LiveData<List<Answer>>
}