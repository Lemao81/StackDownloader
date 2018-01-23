package com.jueggs.stackdownloader.presenter

import android.text.Html
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.data.DataProvider
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.ContentElement
import com.jueggs.stackdownloader.model.DaoSession
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.util.isNougatOrAbove
import com.jueggs.stackdownloader.util.mapNullSafe
import com.jueggs.stackdownloader.util.mapToEntity
import com.jueggs.stackdownloader.view.SearchResultView
import com.jueggs.utils.base.BaseFragmentPresenter
import com.jueggs.utils.base.LifecycleOwnerStub
import com.jueggs.utils.extension.isNetworkConnected
import com.jueggs.utils.extension.join
import javax.inject.Inject

class SearchResultPresenter(private val app: App, private val dataProvider: DataProvider) : BaseFragmentPresenter<SearchResultView>() {
    @Inject
    lateinit var daoSession: DaoSession

    private var questionsCached: List<Question>? = null
    private var answersCached: List<Answer>? = null

    fun presentQuestions(questions: List<Question>) {
        questions.forEach(::setQuestionLabels)
        questionsCached = questions
        view.renderQuestions(questions)
    }

    private fun presentAnswers(question: Question, answers: List<Answer>) {
        answers.forEach(::setContentElementLabels)
        answersCached = answers
        view.renderAnswers(question, answers)
    }

    private fun setQuestionLabels(question: Question) {
        setContentElementLabels(question)
        question.tagsLabel = question.tags.join(", ")
        question.answerCountLabel = question.answerCount.toString()
    }

    private fun setContentElementLabels(element: ContentElement) {
        //TODO replace dummy
        element.creationLabel = "asked 3 hours ago"
        element.scoreLabel = element.score.toString()
        element.bodyFromHtml = if (isNougatOrAbove()) Html.fromHtml(element.body, Html.FROM_HTML_MODE_COMPACT) else Html.fromHtml(element.body)
    }

    fun onHomeButtonClick() = view.showSearchResult()

    fun onQuestionClick(question: Question) {
        if (app.isNetworkConnected()) {
            dataProvider.provideAnswerData(arrayListOf(question.questionId),
                    { itemShellData ->
                        val answers = itemShellData.mapNullSafe().items.map { it.mapNullSafe() }
                        presentAnswers(question, answers)
                        view.showToolbarHomeButton()
                    },
                    { errorMessage ->
                        view.longToast(errorMessage)
                    })
        } else
            view.longToast(R.string.message_no_network)
    }

    fun onDownload() {
        if (app.isNetworkConnected() && questionsCached != null && questionsCached!!.any()) {
            dataProvider.provideAnswerData(questionsCached!!.map { it.questionId },
                    { itemShellData ->
                        val answers = itemShellData.mapNullSafe().items.map { it.mapNullSafe() }

                        daoSession.database.beginTransaction()
                        daoSession.questionEntityDao.insertInTx(questionsCached!!.map { it.mapToEntity() })
                        daoSession.answerEntityDao.insertInTx(answers.map { it.mapToEntity() })

                        try {
                            daoSession.database.setTransactionSuccessful()
                        } catch (e: Exception) {
                            view.longToast("Saving data failed: ${e.message}")
                        } finally {
                            daoSession.database.endTransaction()
                        }
                    },
                    { errorMessage ->
                        view.longToast(errorMessage)
                    })
        } else
            view.longToast(R.string.message_no_network)
    }

    override fun viewStub(): SearchResultView = object : SearchResultView, LifecycleOwnerStub {}
}
