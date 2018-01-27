package com.jueggs.stackdownloader.presenter

import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.data.DataProvider
import com.jueggs.stackdownloader.data.model.DaoSession
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.ContentElement
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.model.SearchCriteria
import com.jueggs.stackdownloader.presenter.interfaces.ISearchResultPresenter
import com.jueggs.stackdownloader.util.mapNullSafe
import com.jueggs.stackdownloader.util.mapToEntity
import com.jueggs.stackdownloader.view.SearchResultView
import com.jueggs.stackdownloader.view.SearchResultViewModel
import com.jueggs.stackdownloader.view.SearchResultViewStub
import com.jueggs.utils.DateRenderer
import com.jueggs.utils.base.BasePresenter
import com.jueggs.utils.extension.isNetworkConnected
import com.jueggs.utils.extension.join
import javax.inject.Inject

class SearchResultPresenter : BasePresenter<SearchResultView, SearchResultViewModel>(), ISearchResultPresenter {
    @Inject
    lateinit var app: App
    @Inject
    lateinit var dataProvider: DataProvider
    @Inject
    lateinit var daoSession: DaoSession
    @Inject
    lateinit var dateRenderer: DateRenderer

    init {
        App.applicationComponent.inject(this)
    }

    override fun onStartSearch(searchCriteria: SearchCriteria) {
        dataProvider.provideQuestionData(searchCriteria,
                { itemShellData ->
                    val questions = itemShellData.mapNullSafe().items.map { it.mapNullSafe() }
                    presentQuestions(questions)
                },
                {
                    view.showLongToast(it)
                })
    }

    private fun presentQuestions(questions: List<Question>) {
        questions.forEach(::setQuestionLabels)
        viewModel.questions = questions
        view.renderQuestions(questions)
    }

    private fun setQuestionLabels(question: Question) {
        setContentElementLabels(question)
        question.tagsLabel = question.tags.join(", ")
        question.answerCountLabel = question.answerCount.toString()
    }

    private fun setContentElementLabels(element: ContentElement) {
        element.creationLabel = dateRenderer.render(element.creationDate)
        element.scoreLabel = element.score.toString()
//        element.bodyFromHtml = if (isNougatOrAbove()) Html.fromHtml(element.body, Html.FROM_HTML_MODE_COMPACT) else Html.fromHtml(element.body)
    }

    override fun onHomeButtonClick() = view.showSearchResult()

    override fun onQuestionClick(question: Question) {
        if (app.isNetworkConnected()) {
            dataProvider.provideAnswerData(arrayListOf(question.questionId),
                    { itemShellData ->
                        val answers = itemShellData.mapNullSafe().items.map { it.mapNullSafe() }
                        presentAnswers(question, answers)
                        view.showToolbarHomeButton()
                    },
                    { errorMessage ->
                        view.showLongToast(errorMessage)
                    })
        } else
            view.showLongToast(R.string.message_no_network)
    }

    private fun presentAnswers(question: Question, answers: List<Answer>) {
        answers.forEach(::setContentElementLabels)
        viewModel.answers = answers
        view.renderAnswers(question, answers)
    }

    override fun onDownload() {
        if (app.isNetworkConnected() && viewModel.questions.any()) {
            dataProvider.provideAnswerData(viewModel.questions.map { it.questionId },
                    { itemShellData ->
                        val answers = itemShellData.mapNullSafe().items.map { it.mapNullSafe() }

                        daoSession.database.beginTransaction()
                        daoSession.questionEntityDao.insertInTx(viewModel.questions.map { it.mapToEntity() })
                        daoSession.answerEntityDao.insertInTx(answers.map { it.mapToEntity() })

                        try {
                            daoSession.database.setTransactionSuccessful()
                        } catch (e: Exception) {
                            view.showLongToast("Saving data failed: ${e.message}")
                        } finally {
                            daoSession.database.endTransaction()
                        }
                    },
                    { errorMessage ->
                        view.showLongToast(errorMessage)
                    })
        } else
            view.showLongToast(R.string.message_no_network)
    }

    override fun viewStub(): SearchResultView = SearchResultViewStub()
}
