package com.jueggs.data

import android.arch.lifecycle.*
import com.jueggs.data.dao.*
import com.jueggs.data.entity.QuestionTagJoinEntity
import com.jueggs.data.mapper.*
import com.jueggs.domain.model.*

class RoomRepository(
        private val questionDao: QuestionDao,
        private val answerDao: AnswerDao,
        private val ownerDao: OwnerDao,
        private val tagDao: TagDao,
        private val questionTagDao: QuestionTagJoinDao
) : Repository {
    override fun getAllQuestionsIncludingTags(): LiveData<List<Question>> {
        return Transformations.map(questionDao.getAllQuestionsIncludingTags(), { questionsWithTags ->
            questionsWithTags.map { questionWithTag -> questionWithTag.question.bo.also { it.tags = questionWithTag.tags.map { it.tagName } } }
        })
    }

    override fun getAnswersOfQuestion(questionId: Long): LiveData<List<Answer>> = Transformations.map(answerDao.getAnswersOfQuestion(questionId), { answers -> answers.map { it.bo } })

    override fun getAllQuestions(): LiveData<List<Question>> = Transformations.map(questionDao.getAllQuestions(), { questions -> questions.map { it.bo } })

    override fun getAllTags(): LiveData<List<Tag>> = Transformations.map(tagDao.getAllTagsAsync(), { entities -> entities.map { it.bo } })

    override fun addTags(tags: List<Tag>) = tagDao.insertTags(tags.map { it.entity })

    override fun addQuestions(questions: List<Question>) {
        questionDao.insertQuestions(questions.map { it.entity })

        val tagEntities = tagDao.getAllTagsSync()

        questions.forEach { question ->
            question.tags.forEach { tag ->
                tagEntities.singleOrNull { it.name == tag }?.let { tagEntity ->
                    questionTagDao.insertQuestionTagJoin(QuestionTagJoinEntity(question.id, tagEntity.name))
                }
            }
        }

        questions.map { it.owner }.filterNotNull().distinctBy { it.id }.forEach { ownerDao.insertOwner(it.entity) }
    }
}