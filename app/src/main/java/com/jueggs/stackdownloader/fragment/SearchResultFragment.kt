package com.jueggs.stackdownloader.fragment

import android.view.MenuItem
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.activity.SearchActivity
import com.jueggs.stackdownloader.adapter.*
import com.jueggs.stackdownloader.bo.*
import com.jueggs.stackdownloader.presenter.interfaces.ISearchResultPresenter
import com.jueggs.stackdownloader.view.*
import com.jueggs.utils.base.*
import com.jueggs.utils.extension.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class SearchResultFragment : BaseFragment<SearchResultView, SearchResultViewModel>(), SearchResultView {
    @Inject
    lateinit var presenter: ISearchResultPresenter

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var answerAdapter: AnswerAdapter

    override fun inject() = App.applicationComponent.inject(this)
    override fun presenter() = presenter as BasePresenter<SearchResultView, SearchResultViewModel>
    override fun self() = this
    override fun layout() = R.layout.fragment_search_result

    override fun viewModel(): SearchResultViewModel = SearchResultViewModel().apply {
        questions = emptyList()
        answers = emptyList()
    }

    override fun initialize() {
        setHasOptionsMenu(true)
    }

    override fun initializeViews(model: SearchResultViewModel) {
        val questionEventHandler = QuestionAdapter.EventHandler(presenter::onQuestionClick)
        questionAdapter = QuestionAdapter().withEventHandler(questionEventHandler) as QuestionAdapter
        answerAdapter = AnswerAdapter()

        recSearchResultQuestions.withAdapter(questionAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
        recSearchResultAnswers.withAdapter(answerAdapter).withVerticalLinearLayoutManager().withSimpleDivider()
        fabDownload.isEnabled = false
    }

    override fun setListeners() {
        fabDownload.onClick { presenter.onDownload() }
    }

    override fun onStartSearch(searchCriteria: SearchCriteria) = presenter.onStartSearch(searchCriteria)

    override fun displayQuestions(questions: List<Question>) {
        recSearchResultAnswers.gone()
        recSearchResultQuestions.visible()
        questionAdapter.setItems(questions)
    }

    override fun displayAnswers(question: Question, answers: List<Answer>) {
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
}