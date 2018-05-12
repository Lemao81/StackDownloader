package com.jueggs.data

import android.arch.lifecycle.LiveData
import com.jueggs.domain.model.*

interface Repository {
    fun addQuestions(questions: List<Question>)

    fun getAllTags(): LiveData<List<String>>

    fun getAllQuestions(): LiveData<List<Question>>

    fun getAnswersOfQuestion(questionId: Long): LiveData<List<Answer>>
}