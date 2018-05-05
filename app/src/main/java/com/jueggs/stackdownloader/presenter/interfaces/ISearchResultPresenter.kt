package com.jueggs.stackdownloader.presenter.interfaces

import com.jueggs.stackdownloader.bo.Question
import com.jueggs.stackdownloader.bo.SearchCriteria

interface ISearchResultPresenter {
    fun onHomeButtonClick()
    fun onQuestionClick(question: Question)
    fun onDownload()
    fun onStartSearch(searchCriteria: SearchCriteria)
}