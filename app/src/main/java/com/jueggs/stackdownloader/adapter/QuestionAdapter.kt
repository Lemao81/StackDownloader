package com.jueggs.stackdownloader.adapter

import com.jueggs.andutils.adapter.SingleLayoutDatabindingAdapter
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.*

class QuestionAdapter : SingleLayoutDatabindingAdapter<Question, Long>(R.layout.list_item_question) {
    override fun getBindingVariableId() = BR.item
    override fun getEventhandlerVariableId(): Int? = BR.handler

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position)

        payloads.forEach { payload ->
            when (payload) {
                Payload.Tags -> {
                    //TODO
                }
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    class EventHandler(private val action: (Question) -> Unit) {
        fun onClick(question: Question) = action(question)
    }

    sealed class Payload {
        object Tags : Payload()
    }
}

