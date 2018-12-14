package com.jueggs.data.repository

import com.jueggs.data.dao.AnswerDao
import com.jueggs.data.dao.OwnerDao
import com.jueggs.data.dao.QuestionDao
import com.jueggs.data.dao.QuestionTagJoinDao
import com.jueggs.data.dao.TagDao
import com.jueggs.data.entity.QuestionTagJoinEntity
import com.jueggs.data.mapper.mapToBo
import com.jueggs.data.mapper.mapToEntity
import com.jueggs.domain.Repository
import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question
import com.jueggs.domain.model.Tag

class RoomRepository(
    private val questionDao: QuestionDao,
    private val answerDao: AnswerDao,
    private val ownerDao: OwnerDao,
    private val tagDao: TagDao,
    private val questionTagDao: QuestionTagJoinDao
) : Repository {
    override fun addTags(tags: List<Tag>) = tagDao.insertAll(tags.map { it.mapToEntity() })

    override fun replaceQuestions(questions: List<Question>) {
        questionDao.replaceAll(questions.map { it.mapToEntity() })

        val tags = tagDao.getAll()
        val questionTagJoins = mutableListOf<QuestionTagJoinEntity>()

        questions.forEach { question ->
            question.tags.forEach { tagName ->
                tags.singleOrNull { it.name == tagName }?.let { tagEntity ->
                    questionTagJoins.add(QuestionTagJoinEntity(question.id, tagEntity.name))
                }
            }
        }

        questionTagDao.replaceAll(questionTagJoins)
        val owners = questions.map { it.owner }.filterNotNull().distinctBy { it.id }.map { it.mapToEntity() }
        ownerDao.replaceAll(owners)
    }

    override fun addAnswers(answers: List<Answer>) {
        answerDao.insertAll(answers.map { it.mapToEntity() })
        ownerDao.insertAll(answers.map { it.owner }.filterNotNull().distinctBy { it.id }.map { it.mapToEntity() })
    }

    override fun getAllTags(): List<Tag> = tagDao.getAll().map { it.mapToBo() }

    override fun getAllTagNames(): List<String> = tagDao.getAllNames()

    override fun getAllQuestionIds(): List<Long> = questionDao.getAllIds()

    override fun getAllQuestionsIncludingOwnerAndTags(): List<Question> = questionDao.getAllIncludingOwnerAndTags().map { join ->
        join.question.mapToBo().also {
            it.tags = join.tags.map { it.tagName }
            it.owner = join.owner.mapToBo()
        }
    }

    override fun getAnswersOfQuestionIncludingOwner(questionId: Long): List<Answer> = answerDao.getAnswersOfQuestionIncludingOwner(questionId).map { join ->
        join.answer.mapToBo().also { it.owner = join.owner.mapToBo() }
    }

    override fun deleteData() {
        questionDao.deleteAll()
        answerDao.deleteAll()
        ownerDao.deleteAll()
        questionTagDao.deleteAll()
    }
}