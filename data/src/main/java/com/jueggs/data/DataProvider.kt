package com.jueggs.data

import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.retrofit.dto.*

interface DataProvider {
    fun provideQuestionData(searchCriteria: SearchCriteria, onSuccess: (ItemShellData<QuestionData>) -> Unit, onFail: (String) -> Unit)

    fun provideAnswerData(questionIds: List<Long>, onSuccess: (ItemShellData<AnswerData>) -> Unit, onFail: (String) -> Unit)
}