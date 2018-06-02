package com.jueggs.data.repository

import com.jueggs.data.dao.*
import com.jueggs.data.entity.QuestionTagJoinEntity
import com.jueggs.data.mapper.*
import com.jueggs.domain.Repository
import com.jueggs.domain.model.*

class RoomRepository(
        private val questionDao: QuestionDao,
        private val answerDao: AnswerDao,
        private val ownerDao: OwnerDao,
        private val tagDao: TagDao,
        private val questionTagDao: QuestionTagJoinDao
) : Repository {
    override fun addTags(tags: List<Tag>) = tagDao.insertAll(tags.map { it.entity })

    override fun addQuestions(questions: List<Question>) {
        questionDao.insertAll(questions.map { it.entity })

        val tagEntities = tagDao.getAll()
        val questionTagJoins = mutableListOf<QuestionTagJoinEntity>()
        questions.forEach { question ->
            question.tags.forEach { tag ->
                tagEntities.singleOrNull { it.name == tag }?.let { tagEntity ->
                    questionTagJoins.add(QuestionTagJoinEntity(question.id, tagEntity.name))
                }
            }
        }

        questionTagDao.insertAll(questionTagJoins)
        ownerDao.insertAll(questions.map { it.owner }.filterNotNull().distinctBy { it.id }.map { it.entity })
    }

    override fun addAnswers(answers: List<Answer>) {
        answerDao.insertAll(answers.map { it.entity })
        ownerDao.insertAll(answers.map { it.owner }.filterNotNull().distinctBy { it.id }.map { it.entity })
    }

    override fun getAllTags(): List<Tag> = tagDao.getAll().map { it.bo }

    override fun getAllTagNames(): List<String> {
        TODO("not implemented")
    }

    override fun getAllQuestionIds(): List<Long> = questionDao.getAllIds()

    override fun getAllQuestionsIncludingOwnerAndTags(): List<Question> = questionDao.getAllIncludingOwnerAndTags().map { join ->
        join.question.bo.also {
            it.tags = join.tags.map { it.tagName }
            it.owner = join.owner.bo
        }
    }

    override fun getAnswersOfQuestionIncludingOwner(questionId: Long): List<Answer> = answerDao.getAnswersOfQuestionIncludingOwner(questionId).map { join ->
        join.answer.bo.also { it.owner = join.owner.bo }
    }

    override fun deleteDownloadedData() {
        questionDao.deleteAll()
        answerDao.deleteAll()
        ownerDao.deleteAll()
        questionTagDao.deleteAll()
    }
}