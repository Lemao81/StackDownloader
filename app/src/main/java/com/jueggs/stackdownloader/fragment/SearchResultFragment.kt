package com.jueggs.stackdownloader.fragment

import android.app.Application
import android.view.MenuItem
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.activity.SearchActivity
import com.jueggs.stackdownloader.adapter.AnswerAdapter
import com.jueggs.stackdownloader.adapter.QuestionAdapter
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.model.SearchCriteria
import com.jueggs.stackdownloader.presenter.interfaces.ISearchResultPresenter
import com.jueggs.stackdownloader.view.SearchResultView
import com.jueggs.stackdownloader.view.SearchResultViewModel
import com.jueggs.utils.base.BaseFragment
import com.jueggs.utils.base.BasePresenter
import com.jueggs.utils.extension.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import javax.inject.Inject

class SearchResultFragment : BaseFragment<SearchResultView, SearchResultViewModel>(), SearchResultView {
    @Inject
    lateinit var presenter: ISearchResultPresenter

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var answerAdapter: AnswerAdapter

    override fun inject() = App.presenterComponent.inject(this)
    override fun presenter() = presenter as BasePresenter<SearchResultView, SearchResultViewModel>
    override fun self() = this
    override fun layout() = R.layout.fragment_search_result

    override fun viewModel(): SearchResultViewModel = SearchResultViewModel().apply {
        questions = arrayListOf()
        answers = arrayListOf()
    }

    override fun initialize() {
        setHasOptionsMenu(true)
    }

    override fun initializeViews(model: SearchResultViewModel) {
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

    override fun onStartSearch(searchCriteria: SearchCriteria) = presenter.onStartSearch(searchCriteria)

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

    //TODO lib
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
}