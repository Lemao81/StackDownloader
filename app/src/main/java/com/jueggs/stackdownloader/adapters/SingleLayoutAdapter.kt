package com.jueggs.stackdownloader.adapters

abstract class SingleLayoutAdapter<in T : Any>(private val items: MutableList<T>, private val layoutId: Int) : BaseAdapter() {
    override fun getLayoutIdForPosition(position: Int): Int = layoutId

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getItemAmount(): Int = items.size

    fun setItems(newItems: List<T>) {
        if (!newItems.any())
            items.clear()
        else {
            items.clear()
            items.addAll(newItems)
        }
        notifyDataSetChanged()
    }
}