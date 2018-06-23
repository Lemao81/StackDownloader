package com.jueggs.stackdownloader.ui.search.view

import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.domain.model.Question
import com.jueggs.resutils.extension.withSimpleDivider
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.adapter.*
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.architecture.ext.sharedViewModel

class SearchResultFragment : BaseFragment<SearchResultFragment.Listener>() {
    val viewModel by sharedViewModel<SearchViewModel>()
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var answerAdapter: AnswerAdapter

    override fun layout() = R.layout.fragment_search_result
    override fun bindingItems() = mapOf(BR.model to viewModel)
    override fun toolbarTitle() = R.string.app_name

    override fun initialize() = setHasOptionsMenu(true)

    override fun initializeViews() {
        questionAdapter = QuestionAdapter().also { it.eventHandler = QuestionAdapter.EventHandler(viewModel::onShowQuestion) }
        answerAdapter = AnswerAdapter()

        recItems.withEmptyView(emptyView).withAdapter(questionAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
    }

    override fun setListeners() {
        viewModel.questions.observeNonNull(this) { questions ->
            questionAdapter.setItems(questions, Question::id)
            recItems.adapter = questionAdapter
        }
        viewModel.getShowQuestionResult().observeNonNull(this) { result ->
            when (result) {
                is NoDataDownloaded -> longToast(R.string.error_no_data_downloaded)
                is Answers -> {
                    if (result.answers.isEmpty())
                        longToast(R.string.error_no_answers)
                    else {
                        answerAdapter.setHeaderAndItems(result.question, result.answers)
                        recItems.scrollToPosition(0)
                        recItems.adapter = answerAdapter
                    }
                }
                is Error -> {
                    longToast(R.string.error_something_wrong)
                    if (AppMode.isDebug) throw result.throwable
                }
            }
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