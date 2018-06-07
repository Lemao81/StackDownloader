package com.jueggs.stackdownloader.ui.search.view

import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.domain.model.Question
import com.jueggs.resutils.extension.withSimpleDivider
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.adapter.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.android.architecture.ext.sharedViewModel

class SearchResultFragment : BaseFragment<SearchResultFragment.Listener>() {
    val viewModel by sharedViewModel<SearchViewModel>()

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var answerAdapter: AnswerAdapter

    override fun layout() = R.layout.fragment_search_result
    override fun bindingItems() = mapOf(BR.model to viewModel.resultViewModel)
    override fun toolbarTitle() = R.string.app_name

    override fun initialize() = setHasOptionsMenu(true)

    override fun initializeViews() {
        questionAdapter = QuestionAdapter().also { it.eventHandler = QuestionAdapter.EventHandler(viewModel.resultViewModel::onShowQuestion) }
        answerAdapter = AnswerAdapter()

        recItems.withAdapter(questionAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
    }

    override fun setListeners() {
        viewModel.resultViewModel.questions.nonNull().observe(this) { questions ->
            questionAdapter.setItems(questions, Question::id)
            recItems.adapter = questionAdapter
        }
        viewModel.resultViewModel.answers.nonNull().observe(this) { (question, answers) ->
            answerAdapter.setHeaderAndItems(question, answers)
            recItems.scrollToPosition(0)
            recItems.adapter = answerAdapter
        }
    }

    override fun onBackPressed(): Boolean {
        if (recItems.adapter is AnswerAdapter) {
            recItems.adapter = questionAdapter
            return true
        }
        return false
    }

    companion object {
        val TAG = SearchResultFragment::class.simpleName

        fun newInstance() = SearchResultFragment()
    }

    interface Listener
}