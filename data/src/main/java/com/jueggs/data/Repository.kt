package com.jueggs.data

import android.arch.lifecycle.LiveData
import com.jueggs.domain.model.*

interface Repository {
    fun addTags(tags: List<Tag>)

    fun addQuestions(questions: List<Question>)

    fun addAnswers(answers: List<Answer>)

    fun getAllTags(): LiveData<List<Tag>>

    fun getAllQuestions(): LiveData<List<Question>>

    fun getAllQuestionsIncludingOwnerAndTagsLive(): LiveData<List<Question>>

    fun getAllQuestionsIncludingOwnerAndTags(): List<Question>

    fun getAnswersOfQuestionLive(questionId: Long): LiveData<List<Answer>>

    fun getAnswersOfQuestion(questionId: Long): List<Answer>

    fun deleteDownloadedData()
}