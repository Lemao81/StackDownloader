package com.jueggs.stackdownloader.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.utils.layoutInflater

abstract class BaseAdapter : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BaseAdapter.ViewHolder = BaseAdapter.ViewHolder(DataBindingUtil.inflate(parent.layoutInflater(), layoutId, parent, false))

    override fun onBindViewHolder(holder: BaseAdapter.ViewHolder, position: Int) = holder.bind(getItemForPosition(position))

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    override fun getItemCount(): Int = getItemAmount()

    abstract fun getLayoutIdForPosition(position: Int): Int

    abstract fun getItemForPosition(position: Int): Any

    abstract fun getItemAmount(): Int

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}