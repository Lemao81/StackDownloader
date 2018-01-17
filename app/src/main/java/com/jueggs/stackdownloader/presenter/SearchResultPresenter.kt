package com.jueggs.stackdownloader.presenter

import android.arch.lifecycle.LifecycleOwner
import android.text.Html
import android.widget.Toast
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.data.DataProvider
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.ContentElement
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.utils.isNougatOrAbove
import com.jueggs.stackdownloader.utils.mapNullSafe
import com.jueggs.utils.extensions.isNetworkConnected
import com.jueggs.utils.extensions.join

class SearchResultPresenter<in T>(private val app: App, private val dataProvider: DataProvider) : Presenter<T>() where T : LifecycleOwner, T : SearchResultView {
    private var view: SearchResultView? = null

    override fun setView(view: T) {
        super.setView(view)
        this.view = view
    }

    fun presentQuestions(questions: List<Question>) {
        questions.forEach(::setQuestionLabels)
        view!!.renderQuestions(questions)
    }

    fun presentAnswers(question: Question, answers: List<Answer>) {
        answers.forEach(::setContentElementLabels)
        view!!.renderAnswers(question, answers)
    }

    private fun setQuestionLabels(question: Question) {
        setContentElementLabels(question)
        question.tagsLabel = question.tags.join(", ")
        question.answerCountLabel = question.answer_count.toString()
    }

    private fun setContentElementLabels(element: ContentElement) {
        //TODO replace dummy
        element.creationLabel = "asked 3 hours ago"
        element.scoreLabel = element.score.toString()
        element.bodyFromHtml = if (isNougatOrAbove()) Html.fromHtml(element.body, Html.FROM_HTML_MODE_COMPACT) else Html.fromHtml(element.body)
    }

    fun onQuestionClick(question: Question) {
        if (app.isNetworkConnected()) {
            dataProvider.provideAnswerData(question.question_id,
                    { itemShellData ->
                        val answers = itemShellData.mapNullSafe().items.map { it.mapNullSafe() }
                        view!!.renderAnswers(question, answers)
                    },
                    { errorMessage ->
                        view!!.longToast(errorMessage)
                    })
        } else
            view!!.longToast("No network connection available")
    }

    override fun dispose() {
        view = null
    }
}

interface SearchResultView {
    fun renderQuestions(questions: List<Question>)
    fun renderAnswers(question: Question, answers: List<Answer>)
    fun longToast(msg: String): Toast
}
