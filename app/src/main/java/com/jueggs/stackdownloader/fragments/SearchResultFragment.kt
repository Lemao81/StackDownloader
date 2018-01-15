package com.jueggs.stackdownloader.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.adapters.AnswerAdapter
import com.jueggs.stackdownloader.adapters.BaseAdapter
import com.jueggs.stackdownloader.adapters.QuestionAdapter
import com.jueggs.stackdownloader.models.*
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.utils.RetrofitCallbackAdapter
import com.jueggs.stackdownloader.utils.dagger
import com.jueggs.utils.extensions.*
import com.jueggs.utils.logNetwork
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.jetbrains.anko.support.v4.longToast
import javax.inject.Inject

class SearchResultFragment : Fragment() {
    @Inject
    lateinit var _stackOverflowClient: StackOverflowClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dagger().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_search_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeComponents()
    }

    private fun initializeComponents() {
        if (context != null) {
            recSearchResult.setVerticalLinearLayoutManager().setTheAdapter(createQuestionAdapter()).setSimpleDivider()
        }
    }

    private fun createQuestionAdapter(questions: MutableList<Question> = arrayListOf()): BaseAdapter {
        val eventHandler = QuestionAdapter.EventHandler(::onQuestionClick)
        return QuestionAdapter(questions).withEventHandler(eventHandler)
    }

    private fun onQuestionClick(question: Question) {
        if (context != null && context!!.isNetworkConnected()) {
            val queryParams = QueryParameter.Builder().build()
            val call = _stackOverflowClient.answersOfQuestion(question.question_id, queryParams.build())
            logNetwork(call.request().url())

            call.enqueue(RetrofitCallbackAdapter<ItemShell<Answer>>(context!!) { itemShell ->
                itemShell.items.forEach { renderContentElement(it) }
                val adapter = AnswerAdapter(question, itemShell.items.toMutableList())
                recSearchResult.setTheAdapter(adapter)
            })
        } else longToast("No network connection available")
    }

    fun setQuestions(questions: List<Question>) {
        questions.forEach { renderQuestion(it) }

        if (recSearchResult.adapter is QuestionAdapter)
            (recSearchResult.adapter as QuestionAdapter).setItems(questions)
        else
            recSearchResult.setTheAdapter(createQuestionAdapter(questions.toMutableList()))
    }

    private fun renderQuestion(question: Question) {
        renderContentElement(question)
        if (question.tags.any()) question.tagsLabel = question.tags.join(", ")
        question.answerCountLabel = question.answer_count.toString()
    }

    private fun renderContentElement(element: ContentElement) {
        //TODO replace dummy
        element.creationLabel = "asked 3 hours ago"
        element.scoreLabel = element.score.toString()
        element.bodyFromHtml = Html.fromHtml(element.body)
    }
}