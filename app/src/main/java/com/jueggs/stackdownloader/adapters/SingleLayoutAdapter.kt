package com.jueggs.stackdownloader.adapters

abstract class SingleLayoutAdapter(private val layoutId: Int) : BaseAdapter() {
    override fun getLayoutIdForPosition(position: Int): Int = layoutId
}