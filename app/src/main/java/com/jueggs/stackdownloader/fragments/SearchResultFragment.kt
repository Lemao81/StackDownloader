package com.jueggs.stackdownloader.fragments

import android.widget.Toast
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.adapters.AnswerAdapter
import com.jueggs.stackdownloader.adapters.QuestionAdapter
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.presenter.SearchResultPresenter
import com.jueggs.stackdownloader.presenter.SearchResultView
import com.jueggs.stackdownloader.utils.gone
import com.jueggs.stackdownloader.utils.visible
import com.jueggs.utils.extensions.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class SearchResultFragment : BaseFragment(), SearchResultView {
    @Inject
    lateinit var _presenter: SearchResultPresenter<SearchResultFragment>
    private lateinit var _questionAdapter: QuestionAdapter
    private lateinit var _answerAdapter: AnswerAdapter

    override fun injectDependencies(component: ApplicationComponent) = component.inject(this)

    override fun getLayout(): Int = R.layout.fragment_search_result

    override fun initializePresenter() = _presenter.setView(this)

    override fun initializeComponents() {
        val questionEventHandler = QuestionAdapter.EventHandler(_presenter::onQuestionClick)
        _questionAdapter = QuestionAdapter().withEventHandler(questionEventHandler) as QuestionAdapter
        _answerAdapter = AnswerAdapter()

        recSearchResultQuestions.setTheAdapter(_questionAdapter).setVerticalLinearLayoutManager().setSimpleDivider()
        recSearchResultAnswers.setTheAdapter(_answerAdapter).setVerticalLinearLayoutManager().setSimpleDivider()
    }

    override fun renderQuestions(questions: List<Question>) {
        recSearchResultAnswers.gone()
        recSearchResultQuestions.visible()
        _questionAdapter.setItems(questions)
    }

    override fun renderAnswers(question: Question, answers: List<Answer>) {
        recSearchResultQuestions.gone()
        recSearchResultAnswers.visible()
        _answerAdapter.setItems(question, answers)
    }

    override fun longToast(msg: String): Toast = ctx.longToast(msg)
}