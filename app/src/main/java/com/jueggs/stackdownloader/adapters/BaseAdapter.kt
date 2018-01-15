package com.jueggs.stackdownloader.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.databinding.ListItemQuestionExtendedBinding
import com.jueggs.stackdownloader.databinding.QuestionAnswerBodyBinding
import com.jueggs.utils.extensions.layoutInflater

abstract class BaseAdapter : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {
    private var eventHandler: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BaseAdapter.ViewHolder = BaseAdapter.ViewHolder(DataBindingUtil.inflate(parent.layoutInflater(), layoutId, parent, false), eventHandler)

    override fun onBindViewHolder(holder: BaseAdapter.ViewHolder, position: Int) = holder.bind(getItemForPosition(position))

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    override fun getItemCount(): Int = getItemAmount()

    fun withEventHandler(eventHandler: Any): BaseAdapter {
        this.eventHandler = eventHandler
        return this
    }

    abstract fun getLayoutIdForPosition(position: Int): Int

    abstract fun getItemForPosition(position: Int): Any

    abstract fun getItemAmount(): Int

    class ViewHolder(private val binding: ViewDataBinding, private val eventHandler: Any?) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            val includeField = binding.javaClass.declaredFields.singleOrNull { it.name == "include" }
            if (includeField != null) {
                val includeBinding = includeField.get(binding)
                val setVariableMethod = includeBinding.javaClass.declaredMethods.singleOrNull { it.name == "setVariable" }
                if (setVariableMethod != null) {
                    setVariableMethod.invoke(includeBinding, BR.item, item)
                }
            }
            if (eventHandler != null) binding.setVariable(BR.eventHandler, eventHandler)
            binding.executePendingBindings()
        }
    }
}