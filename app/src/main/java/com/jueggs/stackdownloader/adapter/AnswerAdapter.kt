package com.jueggs.stackdownloader.adapter

import com.jueggs.andutils.adapter.HeaderLayoutDatabindingAdapter
import com.jueggs.domain.model.*
import com.jueggs.stackdownloader.*

class AnswerAdapter : HeaderLayoutDatabindingAdapter<Question, Answer>(R.layout.list_item_question_as_header, R.layout.list_item_answer, layoutIncluded = true) {
    override fun getBindingVariableId() = BR.item
}