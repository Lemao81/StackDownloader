package com.jueggs.stackdownloader.view

import android.arch.lifecycle.Lifecycle
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.model.SearchCriteria
import com.jueggs.utils.base.BaseView
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

interface SearchResultView : BaseView {
    fun renderQuestions(questions: List<Question>)
    fun renderAnswers(question: Question, answers: List<Answer>)
    fun showSearchResult()
    fun showToolbarHomeButton()
    fun enableDownloadButton()
    fun onStartSearch(searchCriteria: SearchCriteria)
}

@PaperParcel
class SearchResultViewModel : PaperParcelable {
    lateinit var questions: List<Question>
    lateinit var answers: List<Answer>
    var question: Question? = null
    var isDownloadButtonEnabled = false

    companion object {
        @JvmField
        val CREATOR = PaperParcelSearchResultViewModel.CREATOR
        val EMPTY = SearchResultViewModel()
    }
}

class SearchResultViewStub : SearchResultView {
    override fun onStartSearch(searchCriteria: SearchCriteria) {
        TODO("not implemented")
    }

    override fun renderQuestions(questions: List<Question>) {
        TODO("not implemented")
    }

    override fun renderAnswers(question: Question, answers: List<Answer>) {
        TODO("not implemented")
    }

    override fun showSearchResult() {
        TODO("not implemented")
    }

    override fun showToolbarHomeButton() {
        TODO("not implemented")
    }

    override fun enableDownloadButton() {
        TODO("not implemented")
    }

    override fun getLifecycle(): Lifecycle {
        TODO("not implemented")
    }
}