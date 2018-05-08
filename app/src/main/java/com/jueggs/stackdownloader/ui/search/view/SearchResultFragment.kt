package com.jueggs.stackdownloader.ui.search.view

import android.view.MenuItem
import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.domain.model.*
import com.jueggs.resutils.extension.withSimpleDivider
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.adapter.*
import com.jueggs.stackdownloader.ui.search.viewmodel.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.android.architecture.ext.viewModel

class SearchResultFragment : BaseFragment<SearchResultFragment.Listener>() {
    val viewModel by viewModel<SearchResultViewModel>()

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var answerAdapter: AnswerAdapter

    override fun layout() = R.layout.fragment_search_result
    override fun bindingItems() = mapOf(BR.model to SearchResultBindingViewModel(viewModel))

    override fun initialize() {
        setHasOptionsMenu(true)
    }

    override fun initializeViews() {
        questionAdapter = QuestionAdapter().also { it.eventhandler = QuestionAdapter.EventHandler(viewModel) }
        answerAdapter = AnswerAdapter()

        recQuestions.withAdapter(questionAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
        recAnswers.withAdapter(answerAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
        fabDownload.isEnabled = false
    }

    override fun setListeners() {
        viewModel.questions.nonNull().observe(this) { questions ->
            questionAdapter.setItems(questions, Question::id)
        }
        viewModel.answers.nonNull().observe(this) { (question, answers) ->
            answerAdapter.apply {
                setHeader(question)
                setItems(answers)
            }
        }
    }

    override fun onStartSearch(searchCriteria: SearchCriteria) = presenter.onStartSearch(searchCriteria)

    override fun displayQuestions(questions: List<Question>) {
        recAnswers.gone()
        recQuestions.visible()
        questionAdapter.setItems(questions)
    }

    override fun displayAnswers(question: Question, answers: List<Answer>) {
        recQuestions.gone()
        recAnswers.scrollToPosition(0)
        recAnswers.visible()
        answerAdapter.setHeaderAndItems(question, answers)
    }

    override fun showSearchResult() {
        recAnswers.gone()
        recQuestions.visible()
    }

    override fun showToolbarHomeButton() = (activity as SearchActivity).showToolbarHomeButton()

    override fun enableDownloadButton() {
        fabDownload.isEnabled = true
    }

    override fun disableDownloadButton() {
        fabDownload.isEnabled = false
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

    companion object {
        fun newInstance(): SearchCriteriaFragment = SearchCriteriaFragment()
    }

    interface Listener
}