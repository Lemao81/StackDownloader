package com.jueggs.stackdownloader.adapters

import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.model.Answer
import com.jueggs.stackdownloader.model.Question

class AnswerAdapter(private var question: Question?, private val answers: MutableList<Answer>) : BaseAdapter() {
    constructor() : this(null, arrayListOf())

    override fun getLayoutIdForPosition(position: Int): Int = if (position == 0) R.layout.list_item_question_extended else R.layout.list_item_answer

    override fun getItemForPosition(position: Int): Any = if (position == 0) question ?: Any() else answers[position - 1]

    override fun getItemAmount(): Int = answers.size + 1

    fun setItems(newQuestion: Question, newAnswers: List<Answer>) {
        question = newQuestion
        if (!newAnswers.any()) {
            answers.clear()
        } else {
            answers.clear()
            answers.addAll(newAnswers)
        }
        notifyDataSetChanged()
    }
}