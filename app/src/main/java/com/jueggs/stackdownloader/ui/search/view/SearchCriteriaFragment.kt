package com.jueggs.stackdownloader.ui.search.view

import android.widget.TextView
import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.architecture.ext.viewModel
import java.util.*

class SearchCriteriaFragment : BaseFragment<SearchCriteriaFragment.Listener>() {
    val viewModel by viewModel<SearchViewModel>()

    override fun layout() = R.layout.fragment_search_criteria
    override fun bindingItems(): Map<Int, Any>? = hashMapOf(BR.model to viewModel)

    override fun onInitialStart() {
        viewModel.onInitialStart()
    }

    override fun setListeners() {
        viewModel.availableTags.nonNull().observe(this) { tags -> autoTxtTags.withSimpleAdapter(tags.map { it.name }) }
        viewModel.selectedTags.nonNull().observe(this) { tags ->
            linTagContainer.removeAllViews()
            tags.forEach { linTagContainer.addView(TextView(linTagContainer.context).apply { text = it }) }
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