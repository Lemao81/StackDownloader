package com.jueggs.stackdownloader.adapter

import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.bo.*
import com.jueggs.utils.adapter.HeaderLayoutDatabindingAdapter

class AnswerAdapter : HeaderLayoutDatabindingAdapter<Question, Answer>(R.layout.list_item_question_as_header, R.layout.list_item_answer, layoutIncluded = true) {
    override fun getBindingVariableId() = BR.item
}