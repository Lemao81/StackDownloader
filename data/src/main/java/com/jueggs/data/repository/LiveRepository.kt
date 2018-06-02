package com.jueggs.data.repository

import android.arch.lifecycle.LiveData
import com.jueggs.domain.model.*

interface LiveRepository {
    fun getAllQuestionsIncludingOwnerAndTags(): LiveData<List<Question>>

    fun getAnswersOfQuestionIncludingOwner(questionId: Long): LiveData<List<Answer>>

    fun getAllTags(): LiveData<List<Tag>>
}