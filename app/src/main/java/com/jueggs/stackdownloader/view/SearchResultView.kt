package com.jueggs.stackdownloader.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.os.Parcelable
import com.jueggs.stackdownloader.bo.*
import com.jueggs.utils.base.BaseView
import kotlinx.android.parcel.Parcelize

interface SearchResultView : BaseView {
    fun displayQuestions(questions: List<Question>)
    fun displayAnswers(question: Question, answers: List<Answer>)
    fun showSearchResult()
    fun showToolbarHomeButton()
    fun enableDownloadButton()
    fun disableDownloadButton()
    fun onStartSearch(searchCriteria: SearchCriteria)
}

@SuppressLint("ParcelCreator")
@Parcelize
class SearchResultViewModel(
        var questions: List<Question> = emptyList(),
        var answers: List<Answer> = emptyList(),
        var question: Question? = null,
        var isDownloadButtonEnabled: Boolean = false) : Parcelable {

    companion object {
        val EMPTY = SearchResultViewModel()
    }
}

class SearchResultViewStub : SearchResultView {
    override fun disableDownloadButton() {
        TODO("not implemented")
    }

    override fun onStartSearch(searchCriteria: SearchCriteria) {
        TODO("not implemented")
    }

    override fun displayQuestions(questions: List<Question>) {
        TODO("not implemented")
    }

    override fun displayAnswers(question: Question, answers: List<Answer>) {
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