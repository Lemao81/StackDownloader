package com.jueggs.stackdownloader.adapters

import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.models.Answer
import com.jueggs.stackdownloader.models.Question

class AnswerAdapter(private var question: Question, private val answers: MutableList<Answer>) : BaseAdapter() {
    override fun getLayoutIdForPosition(position: Int): Int = if (position == 0) R.layout.list_item_question_extended else R.layout.list_item_answer

    override fun getItemForPosition(position: Int): Any = if (position == 0) question else answers[position - 1]

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