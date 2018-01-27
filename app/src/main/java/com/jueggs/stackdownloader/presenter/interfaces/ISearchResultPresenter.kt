package com.jueggs.stackdownloader.presenter.interfaces

import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.model.SearchCriteria

interface ISearchResultPresenter {
    fun onHomeButtonClick()
    fun onQuestionClick(question: Question)
    fun onDownload()
    fun onStartSearch(searchCriteria: SearchCriteria)
}