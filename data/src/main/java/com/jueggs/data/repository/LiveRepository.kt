package com.jueggs.data.repository

import androidx.lifecycle.LiveData
import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question

interface LiveRepository {
    fun getAllQuestionsIncludingOwnerAndTags(): LiveData<List<Question>>

    fun getAnswersOfQuestionIncludingOwner(questionId: Long): LiveData<List<Answer>>

    fun getAllTagNames(): LiveData<List<String>>
}