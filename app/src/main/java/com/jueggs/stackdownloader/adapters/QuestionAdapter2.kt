package com.jueggs.stackdownloader.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.models.Question
import com.jueggs.stackdownloader.utils.layoutInflater


class QuestionAdapter2(private var questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(DataBindingUtil.inflate(parent.layoutInflater(), viewType, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(questions[position])

    override fun getItemCount(): Int = questions.size

    fun setQuestions(questions: List<Question>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}