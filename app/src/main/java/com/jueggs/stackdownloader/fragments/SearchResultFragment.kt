package com.jueggs.stackdownloader.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.adapters.QuestionAdapter
import com.jueggs.stackdownloader.models.Question
import com.jueggs.stackdownloader.utils.addAdapter
import com.jueggs.stackdownloader.utils.addVerticalLinearLayoutManager
import com.jueggs.stackdownloader.utils.join
import com.jueggs.utils.extensions.addSimpleDivider
import kotlinx.android.synthetic.main.fragment_search_result.*

class SearchResultFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_search_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeComponents()
    }

    private fun initializeComponents() {
        if (context != null) {
            recSearchResult.addVerticalLinearLayoutManager(context!!).addAdapter(QuestionAdapter(arrayListOf())).addSimpleDivider()
        }
    }

    fun setQuestions(questions: List<Question>) {
        questions.forEach {
            if (it.tags.any()) it.tagsLabel = it.tags.join(", ")
            it.creationLabel = "asked 3 hours ago"
            it.answerLabel = it.answer_count.toString()
            it.scoreLabel = it.score.toString()
        }
        (recSearchResult.adapter as QuestionAdapter).setQuestions(questions)
    }

    companion object {
        fun newInstance(): SearchResultFragment = SearchResultFragment()
    }
}