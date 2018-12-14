package com.jueggs.stackdownloader.adapter

import com.jueggs.andutils.adapter.HeaderLayoutDatabindingAdapter
import com.jueggs.domain.model.Answer
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.BR

class AnswerAdapter : HeaderLayoutDatabindingAdapter<Question, Answer>(R.layout.list_item_question_as_header, R.layout.list_item_answer, layoutIncluded = true) {
    override fun getBindingVariableId() = BR.item
}