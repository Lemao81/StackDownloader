package com.jueggs.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jueggs.data.dao.AnswerDao
import com.jueggs.data.dao.QuestionDao
import com.jueggs.data.dao.TagDao
import com.jueggs.data.mapper.mapToBo
import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question

class RoomLiveRepository(
    private val questionDao: QuestionDao,
    private val answerDao: AnswerDao,
    private val tagDao: TagDao
) : LiveRepository {

    override fun getAllQuestionsIncludingOwnerAndTags(): LiveData<List<Question>> {
        return Transformations.map(questionDao.getAllIncludingOwnerAndTagsLiveDistinct()) { questionsWithTags ->
            questionsWithTags.map { questionWithTag -> questionWithTag.question.mapToBo().also { it.tags = questionWithTag.tags.map { it.tagName } } }
        }
    }

    override fun getAnswersOfQuestionIncludingOwner(questionId: Long): LiveData<List<Answer>> = Transformations.map(answerDao.getAnswersOfQuestionIncludingOwnerLive(questionId)) { joins ->
        joins.map { join -> join.answer.mapToBo().also { it.owner = join.owner.mapToBo() } }
    }

    override fun getAllTagNames(): LiveData<List<String>> = tagDao.getAllNamesLiveDistinct()
}