package com.jueggs.stackdownloader.adapters

import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.models.Question

class QuestionAdapter(private var questions: List<Question>) : SingleLayoutAdapter(R.layout.list_item_question) {
    override fun getItemForPosition(position: Int): Any = questions[position]

    override fun getItemAmount(): Int = questions.size

    fun setQuestions(questions: List<Question>) {
        this.questions = questions
        notifyDataSetChanged()
    }
}