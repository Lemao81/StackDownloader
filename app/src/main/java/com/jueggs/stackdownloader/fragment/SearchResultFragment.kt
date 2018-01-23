package com.jueggs.stackdownloader.fragment

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.activity.SearchActivity
import com.jueggs.stackdownloader.adapter.AnswerAdapter
import com.jueggs.stackdownloader.adapter.QuestionAdapter
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.presenter.SearchResultPresenter
import com.jueggs.stackdownloader.view.SearchResultView
import com.jueggs.utils.base.BaseFragment
import com.jueggs.utils.extension.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class SearchResultFragment : BaseFragment<SearchResultView>(), SearchResultView {
    @Inject
    lateinit var presenter: SearchResultPresenter
    @Inject
    lateinit var app: App

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var answerAdapter: AnswerAdapter

    override fun inject() = app.applicationComponent.inject(this)
    override fun presenter() = presenter
    override fun self() = this
    override fun layout() = R.layout.fragment_search_result

    override fun initialize() {
        setHasOptionsMenu(true)
    }

    override fun initializeViews() {
        val questionEventHandler = QuestionAdapter.EventHandler(presenter::onQuestionClick)
        questionAdapter = QuestionAdapter().withEventHandler(questionEventHandler) as QuestionAdapter
        answerAdapter = AnswerAdapter()

        recSearchResultQuestions.setTheAdapter(questionAdapter).setVerticalLinearLayoutManager().setSimpleDivider()
        recSearchResultAnswers.setTheAdapter(answerAdapter).setVerticalLinearLayoutManager().setSimpleDivider()
        fabDownload.isEnabled = false
    }

    override fun setListeners() {
        fabDownload.setOnClickListener { presenter.onDownload() }
    }

    override fun restoreState(savedInstanceState: Bundle) {
        fabDownload.isEnabled = savedInstanceState.getBoolean(STATE_DOWNLOAD_BUTTON_ENABLED)
    }

    override fun renderQuestions(questions: List<Question>) {
        recSearchResultAnswers.gone()
        recSearchResultQuestions.visible()
        questionAdapter.setItems(questions)
    }

    override fun renderAnswers(question: Question, answers: List<Answer>) {
        recSearchResultQuestions.gone()
        recSearchResultAnswers.scrollToPosition(0)
        recSearchResultAnswers.visible()
        answerAdapter.setHeaderAndItems(question, answers)
    }

    override fun showSearchResult() {
        recSearchResultAnswers.gone()
        recSearchResultQuestions.visible()
    }

    override fun showToolbarHomeButton() = (activity as SearchActivity).showToolbarHomeButton()

    override fun enableDownloadButton() {
        fabDownload.isEnabled = true
    }

    override fun longToast(msg: String): Toast = ctx.longToast(msg)
    override fun longToast(resId: Int): Toast = ctx.longToast(resId)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                presenter.onHomeButtonClick()
                (activity as SearchActivity).hideToolbarHomeButton()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.withData(STATE_DOWNLOAD_BUTTON_ENABLED to fabDownload.isEnabled)
    }

    companion object {
        const val STATE_DOWNLOAD_BUTTON_ENABLED = "STATE_DOWNLOAD_BUTTON_ENABLED"
    }
}