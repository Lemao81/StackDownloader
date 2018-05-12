package com.jueggs.stackdownloader.ui.search.view

import android.os.Bundle
import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.domain.model.*
import com.jueggs.resutils.extension.withSimpleDivider
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.adapter.*
import com.jueggs.stackdownloader.model.dto.SearchCriteriaDto
import com.jueggs.stackdownloader.ui.search.viewmodel.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.android.architecture.ext.viewModel

class SearchResultFragment : BaseFragment<SearchResultFragment.Listener>() {
    val viewModel by viewModel<SearchViewModel>()

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var answerAdapter: AnswerAdapter

    override fun layout() = R.layout.fragment_search_result
    override fun bindingItems() = mapOf(BR.model to SearchResultBindingViewModel(viewModel))

    override fun initialize() {
        setHasOptionsMenu(true)
    }

    override fun initializeViews() {
        questionAdapter = QuestionAdapter().also { it.eventHandler = QuestionAdapter.EventHandler(viewModel) }
        answerAdapter = AnswerAdapter()

        recQuestions.withAdapter(questionAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
        recAnswers.withAdapter(answerAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
        fabDownload.isEnabled = false
    }

    override fun setListeners() {
        viewModel.search.nonNull().observe(this) { searchCriteria ->

        }
        viewModel.questions.nonNull().observe(this) { questions -> questionAdapter.setItems(questions, Question::id) }
        viewModel.answers.nonNull().observe(this) { liveData ->
            liveData.nonNull().observe(this) { (question, answers) ->
                answerAdapter.setHeaderAndItems(question, answers)
                recAnswers.scrollToPosition(0)
            }
        }
    }

    override fun showToolbarHomeButton() = (activity as SearchActivity).showToolbarHomeButton()

    override fun enableDownloadButton() {
        fabDownload.isEnabled = true
    }

    override fun disableDownloadButton() {
        fabDownload.isEnabled = false
    }

    override fun onMenuItemSelected(id: Int) =
            when (id) {
                android.R.id.home -> {
                    presenter.onHomeButtonClick()
                    (activity as SearchActivity).hideToolbarHomeButton()
                    true
                }
                else -> null
            }

    companion object {
        fun newInstance(): SearchResultFragment = SearchResultFragment()
    }

    interface Listener
}