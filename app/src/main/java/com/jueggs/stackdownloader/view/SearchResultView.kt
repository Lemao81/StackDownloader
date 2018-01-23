package com.jueggs.stackdownloader.view

import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.Question
import com.jueggs.utils.base.BaseView

interface SearchResultView : BaseView {
    fun renderQuestions(questions: List<Question>) {
        TODO("not implemented")
    }

    fun renderAnswers(question: Question, answers: List<Answer>) {
        TODO("not implemented")
    }

    fun showSearchResult() {
        TODO("not implemented")
    }

    fun showToolbarHomeButton() {
        TODO("not implemented")
    }

    fun enableDownloadButton() {
        TODO("not implemented")
    }
}