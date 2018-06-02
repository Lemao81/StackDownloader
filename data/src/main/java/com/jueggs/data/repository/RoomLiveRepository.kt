package com.jueggs.data.repository

import android.arch.lifecycle.*
import com.jueggs.data.dao.*
import com.jueggs.data.mapper.bo
import com.jueggs.domain.model.*

class RoomLiveRepository(
        private val questionDao: QuestionDao,
        private val answerDao: AnswerDao,
        private val tagDao: TagDao
) : LiveRepository {

    override fun getAllQuestionsIncludingOwnerAndTags(): LiveData<List<Question>> {
        return Transformations.map(questionDao.getAllIncludingOwnerAndTagsLive(), { questionsWithTags ->
            questionsWithTags.map { questionWithTag -> questionWithTag.question.bo.also { it.tags = questionWithTag.tags.map { it.tagName } } }
        })
    }

    override fun getAnswersOfQuestionIncludingOwner(questionId: Long): LiveData<List<Answer>> = Transformations.map(answerDao.getAnswersOfQuestionIncludingOwnerLive(questionId), { joins ->
        joins.map { join -> join.answer.bo.also { it.owner = join.owner.bo } }
    })

    override fun getAllTagNames(): LiveData<List<String>> = tagDao.getAllNamesLive()
}