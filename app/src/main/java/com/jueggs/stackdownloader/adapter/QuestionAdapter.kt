package com.jueggs.stackdownloader.adapter

import com.jueggs.andutils.adapter.SingleLayoutDatabindingAdapter
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel

class QuestionAdapter : SingleLayoutDatabindingAdapter<Question, Long>(R.layout.list_item_question) {
    override fun getBindingVariableId() = BR.item
    override fun getEventhandlerVariableId(): Int? = BR.handler

    class EventHandler(private val viewModel: SearchViewModel) {
        fun onClick(question: Question) = viewModel.onShowQuestion(question)
    }
}