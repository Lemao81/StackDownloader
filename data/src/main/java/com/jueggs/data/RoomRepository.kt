package com.jueggs.data

import android.arch.lifecycle.*
import com.jueggs.data.dao.*
import com.jueggs.data.mapper.*
import com.jueggs.domain.model.*

class RoomRepository(
        private val questionDao: QuestionDao,
        private val answerDao: AnswerDao,
        private val ownerDao: OwnerDao,
        private val tagDao: TagDao
) : Repository {
    override fun addTags(tags: List<Tag>) = tagDao.insertTags(tags.map { it.entity })

    override fun addQuestions(questions: List<Question>) = questionDao.insertQuestions(questions.map { it.entity })

    override fun getAnswersOfQuestion(questionId: Long): LiveData<List<Answer>> = Transformations.map(answerDao.getAnswersOfQuestion(questionId), { answers -> answers.map { it.bo } })

    override fun getAllQuestions(): LiveData<List<Question>> = Transformations.map(questionDao.getAllQuestions(), { questions -> questions.map { it.bo } })

    override fun getAllTags(): LiveData<List<Tag>> = Transformations.map(tagDao.getAllTags(), { entities -> entities.map { it.bo } })
}