package com.jueggs.domain

import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question
import com.jueggs.domain.model.Tag

interface Repository {
    fun addTags(tags: List<Tag>)

    fun addAnswers(answers: List<Answer>)

    fun replaceQuestions(questions: List<Question>)

    fun getAllTags(): List<Tag>

    fun getAllTagNames(): List<String>

    fun getAllQuestionIds(): List<Long>

    fun getAllQuestionsIncludingOwnerAndTags(): List<Question>

    fun getAnswersOfQuestionIncludingOwner(questionId: Long): List<Answer>

    fun deleteData()
}