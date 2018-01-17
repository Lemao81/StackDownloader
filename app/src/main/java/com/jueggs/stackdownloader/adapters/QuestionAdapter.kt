package com.jueggs.stackdownloader.adapters

import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.model.Question

class QuestionAdapter : SingleLayoutAdapter<Question>(arrayListOf(), R.layout.list_item_question) {

    class EventHandler(private val onClick: (Question) -> Unit) {
        fun onQuestionClick(question: Question) = onClick(question)
    }
}