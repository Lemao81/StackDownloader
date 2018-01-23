package com.jueggs.stackdownloader.adapter

import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.model.Question
import com.jueggs.utils.adapter.SingleLayoutDatabindingAdapter

class QuestionAdapter : SingleLayoutDatabindingAdapter<Question>(R.layout.list_item_question) {
    override fun getBindingVariableId() = BR.item
    override fun getEventhandlerVariableId(): Int? = BR.eventHandler

    class EventHandler(private val onClick: (Question) -> Unit) {
        fun onQuestionClick(question: Question) = onClick(question)
    }
}