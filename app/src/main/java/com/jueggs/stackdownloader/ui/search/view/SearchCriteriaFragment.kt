package com.jueggs.stackdownloader.ui.search.view

import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchCriteriaViewModel
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.koin.android.architecture.ext.viewModel

class SearchCriteriaFragment : BaseFragment<SearchCriteriaFragment.Listener>() {
    val viewModel by viewModel<SearchCriteriaViewModel>()

    override fun layout() = R.layout.fragment_search_criteria

    override fun initializeViews() {
        spnOrderBy.withSimpleAdapter(ORDER_ASC, ORDER_DESC)
        spnSortBy.withSimpleAdapter(SORT_CREATION, SORT_ACTIVITY, SORT_HOT, SORT_WEEK, SORT_MONTH, SORT_VOTES)
    }

    override fun setListeners() {
        ibtnSearch.onClick { viewModel.onStartSearch() }
    }

    override fun getPageSize(): String = edtLimitTo.asString()
    override fun getSortOrder(): String = spnSortBy.asString()
    override fun getOrderType(): String = spnOrderBy.asString()
    override fun getMinScore(): String = edtScore.asString()
    override fun getTags(): List<String> = tags

    companion object {
        fun newInstance(): SearchCriteriaFragment = SearchCriteriaFragment()
    }

    interface Listener
}
