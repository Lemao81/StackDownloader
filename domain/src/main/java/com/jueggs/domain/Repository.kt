package com.jueggs.domain

import com.jueggs.domain.model.*

interface Repository {
    fun addTags(tags: List<Tag>)

    fun addQuestions(questions: List<Question>)

    fun addAnswers(answers: List<Answer>)

    fun getAllTags(): List<Tag>

    fun getAllTagNames(): List<String>

    fun getAllQuestionIds(): List<Long>

    fun getAllQuestionsIncludingOwnerAndTags(): List<Question>

    fun getAnswersOfQuestionIncludingOwner(questionId: Long): List<Answer>

    fun deleteDownloadedData()
}