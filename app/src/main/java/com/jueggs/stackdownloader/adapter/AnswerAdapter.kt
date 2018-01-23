package com.jueggs.stackdownloader.adapter

import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.Question
import com.jueggs.utils.adapter.HeaderLayoutDatabindingAdapter

class AnswerAdapter : HeaderLayoutDatabindingAdapter<Question, Answer>(R.layout.list_item_question_as_header, R.layout.list_item_answer, layoutIncluded = true) {
    override fun getBindingVariableId() = BR.item
}