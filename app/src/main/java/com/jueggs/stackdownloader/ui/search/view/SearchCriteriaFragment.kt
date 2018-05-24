package com.jueggs.stackdownloader.ui.search.view

import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.customview.stackoverflowtag.StackoverflowTag
import com.jueggs.stackdownloader.BR
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.koin.android.architecture.ext.sharedViewModel
import java.util.*

class SearchCriteriaFragment : BaseFragment<SearchCriteriaFragment.Listener>() {
    val viewModel by sharedViewModel<SearchViewModel>()

    override fun layout() = R.layout.fragment_search_criteria
    override fun bindingItems(): Map<Int, Any>? = hashMapOf(BR.model to viewModel)

    override fun onInitialStart() = viewModel.onInitialStart()

    override fun setListeners() {
        viewModel.availableTags.nonNull().observe(this) { tags -> autoTxtTags.withSimpleAdapter(tags.map { it.name }) }
        viewModel.selectedTags.nonNull().observe(this) { tags ->
            if (context != null) {
                linTagContainer.removeAllViews()
                tags.forEach { tag ->
                    val tagView = StackoverflowTag(context!!, tag)
                    tagView.onClick { viewModel.onRemoveTag(tagView.tagName) }
                    linTagContainer.addView(tagView)
                }
            }
        }
        viewModel.errors.nonNull().observe(this) { toast(it) }
        viewModel.onEditFromDate.nonNull().observe(this) { datePicker(viewModel.fromDate.getOr(Date()), viewModel.fromDate::set) }
        viewModel.onEditToDate.nonNull().observe(this) { datePicker(viewModel.toDate.getOr(Date()), viewModel.toDate::set) }
    }

    companion object {
        fun newInstance() = SearchCriteriaFragment()
    }

    interface Listener
}