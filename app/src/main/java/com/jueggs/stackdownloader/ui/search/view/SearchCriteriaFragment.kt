package com.jueggs.stackdownloader.ui.search.view

import android.widget.TextView
import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.viewmodel.*
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.architecture.ext.viewModel

class SearchCriteriaFragment : BaseFragment<SearchCriteriaFragment.Listener>() {
    val viewModel by viewModel<SearchCriteriaViewModel>()

    override fun layout() = R.layout.fragment_search_criteria
    override fun bindingItems(): Map<Int, Any>? = hashMapOf(BR.model to SearchCriteriaBindingViewModel(viewModel))

    override fun setListeners() {
        viewModel.tags.nonNull().observe(this) { tags ->
            linTagContainer.removeAllViews()
            tags.forEach { linTagContainer.addView(TextView(linTagContainer.context).apply { text = it }) }
        }
        viewModel.errors.nonNull().observe(this) { toast(it) }
        viewModel.search.nonNull().observe(this) { listener?.onStartSearch(it) }
    }

    companion object {
        fun newInstance(): SearchCriteriaFragment = SearchCriteriaFragment()
    }

    interface Listener {
        fun onStartSearch(searchCriteria: SearchCriteria)
    }
}