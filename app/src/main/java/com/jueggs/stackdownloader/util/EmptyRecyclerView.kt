package com.jueggs.stackdownloader.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jueggs.andutils.extension.gone
import com.jueggs.andutils.extension.visible

class EmptyRecyclerView : RecyclerView {
    private var emptyView: View? = null

    private val dataObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            initEmptyView()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            initEmptyView()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            initEmptyView()
        }
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private fun initEmptyView() {
        if (emptyView != null && (adapter == null || adapter?.itemCount == 0)) {
            gone()
            emptyView?.visible()
        } else {
            visible()
            emptyView?.gone()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        super.setAdapter(adapter)
        oldAdapter?.unregisterAdapterDataObserver(dataObserver)
        adapter?.registerAdapterDataObserver(dataObserver)
    }

    fun withEmptyView(view: View): RecyclerView {
        this.emptyView = view
        initEmptyView()
        return this
    }
}