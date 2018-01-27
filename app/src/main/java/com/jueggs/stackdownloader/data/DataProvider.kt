package com.jueggs.stackdownloader.data

import com.jueggs.stackdownloader.retrofit.model.AnswerData
import com.jueggs.stackdownloader.retrofit.model.ItemShellData
import com.jueggs.stackdownloader.retrofit.model.QuestionData
import com.jueggs.stackdownloader.model.SearchCriteria

interface DataProvider {
    fun provideQuestionData(searchCriteria: SearchCriteria, onSuccess: (ItemShellData<QuestionData>) -> Unit, onFail: (String) -> Unit)

    fun provideAnswerData(questionIds: List<Long>, onSuccess: (ItemShellData<AnswerData>) -> Unit, onFail: (String) -> Unit)
}