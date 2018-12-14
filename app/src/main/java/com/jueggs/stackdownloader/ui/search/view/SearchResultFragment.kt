package com.jueggs.stackdownloader.ui.search.view

import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.domain.model.Question
import com.jueggs.resutils.extension.withSimpleDivider
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R.id.emptyView
import com.jueggs.stackdownloader.R.id.recItems
import com.jueggs.stackdownloader.adapter.*
import com.jueggs.stackdownloader.ui.search.delegate.AppModeDelegate
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchResultFragment : BaseFragment() {
    val viewModel by sharedViewModel<SearchViewModel>()
    val delegate: AppModeDelegate<SearchResultFragment> by inject(SearchResultFragment::class.simpleName ?: throw IllegalStateException())
    private val questionAdapter: QuestionAdapter by lazy { QuestionAdapter().also { it.eventHandler = QuestionAdapter.EventHandler(viewModel::onShowQuestion) } }
    private val answerAdapter: AnswerAdapter by lazy { AnswerAdapter() }

    override fun layout() = R.layout.fragment_search_result
    override fun bindingItems() = mapOf(BR.model to viewModel)
    override fun toolbarTitle() = R.string.app_name

    override fun initialize() = setHasOptionsMenu(true)

    override fun initializeViews() {
        recItems.withEmptyView(emptyView).withAdapter(questionAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
    }

    override fun setListeners() {
        delegate.setListeners(this)

        viewModel.questions.nonNull().observe(this) { questions ->
            questionAdapter.setItems(questions, Question::id)
            recItems.adapter = questionAdapter
        }
        viewModel.getShowQuestionResult().nonNull().observe(this) { result ->
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

//    override fun onBackPressed(): Boolean {
//        if (recItems.adapter is AnswerAdapter) {
//            recItems.adapter = questionAdapter
//            return true
//        }
//        return false
//    }

    companion object {
        val TAG = SearchResultFragment::class.simpleName

        fun newInstance() = SearchResultFragment()
    }
}