package com.jueggs.stackdownloader.ui.search.view

import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.customview.stackoverflowtag.StackoverflowTag
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.koin.android.architecture.ext.sharedViewModel
import java.util.*

class SearchCriteriaFragment : BaseFragment<SearchCriteriaFragment.Listener>() {
    val viewModel by sharedViewModel<SearchViewModel>()

    override fun layout() = R.layout.fragment_search_criteria
    override fun bindingItems() = mapOf(BR.model to viewModel.criteriaViewModel)
    override fun toolbarTitle() = R.string.title_search_criteria

    override fun setListeners() {
        viewModel.criteriaViewModel.availableTags.nonNull().observe(this) { autoTxtTags.withSimpleAdapter(it) }
        viewModel.criteriaViewModel.selectedTags.nonNull().observe(this) { tags ->
            if (context != null) {
                linTagContainer.removeAllViews()
                tags.forEach { tag ->
                    val tagView = StackoverflowTag(context!!, tag, 7)
                    tagView.onClick { viewModel.criteriaViewModel.onRemoveTag(tagView.tagName) }
                    linTagContainer.addView(tagView)
                }
            }
        }
        viewModel.criteriaViewModel.onEditFromDate.nonNull().observe(this) {
            datePicker(viewModel.criteriaViewModel.fromDate.getOr(Date()), viewModel.criteriaViewModel.fromDate::set)
        }
        viewModel.criteriaViewModel.onEditToDate.nonNull().observe(this) {
            datePicker(viewModel.criteriaViewModel.toDate.getOr(Date()), viewModel.criteriaViewModel.toDate::set)
        }
    }

    companion object {
        val TAG = SearchCriteriaFragment::class.simpleName

        fun newInstance() = SearchCriteriaFragment()
    }

    interface Listener
}