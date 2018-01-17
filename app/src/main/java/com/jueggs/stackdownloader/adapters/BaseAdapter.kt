package com.jueggs.stackdownloader.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.utils.findDeclaredField
import com.jueggs.stackdownloader.utils.findDeclaredMethod
import com.jueggs.utils.extensions.layoutInflater
import java.lang.reflect.Modifier

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
            bindIncludedLayoutIfExist(item)
            if (eventHandler != null) binding.setVariable(BR.eventHandler, eventHandler)
            binding.executePendingBindings()
        }

        private fun bindIncludedLayoutIfExist(item: Any) {
            val includeBinding = binding.findDeclaredField(INCLUDED_LAYOUT_ID_IDENTIFIER, Modifier.PUBLIC)?.get(binding)
            includeBinding?.findDeclaredMethod(ViewDataBinding::setVariable.name, Modifier.PUBLIC)?.invoke(includeBinding, BR.item, item)
        }
    }

    companion object {
        const val INCLUDED_LAYOUT_ID_IDENTIFIER = "include"
    }
}