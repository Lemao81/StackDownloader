package com.jueggs.stackdownloader.adapters

import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.models.Question

class QuestionAdapter : SingleLayoutAdapter<Question> {
    constructor(questions: MutableList<Question>) : super(questions, R.layout.list_item_question)

    class EventHandler(private val onClick: (Question) -> Unit) {
        fun onQuestionClick(question: Question) = onClick(question)
    }
}