package com.jueggs.stackdownloader.data

import com.jueggs.stackdownloader.retrofit.dto.AnswerData
import com.jueggs.stackdownloader.retrofit.dto.ItemShellData
import com.jueggs.stackdownloader.retrofit.dto.QuestionData
import com.jueggs.stackdownloader.bo.SearchCriteria

interface DataProvider {
    fun provideQuestionData(searchCriteria: SearchCriteria, onSuccess: (ItemShellData<QuestionData>) -> Unit, onFail: (String) -> Unit)

    fun provideAnswerData(questionIds: List<Long>, onSuccess: (ItemShellData<AnswerData>) -> Unit, onFail: (String) -> Unit)
}