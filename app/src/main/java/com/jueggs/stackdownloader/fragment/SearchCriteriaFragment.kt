package com.jueggs.stackdownloader.fragment

import android.app.Application
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.presenter.interfaces.ISearchCriteriaPresenter
import com.jueggs.stackdownloader.util.*
import com.jueggs.stackdownloader.view.SearchCriteriaView
import com.jueggs.stackdownloader.view.SearchCriteriaViewModel
import com.jueggs.utils.base.BaseFragment
import com.jueggs.utils.base.BasePresenter
import com.jueggs.utils.extension.asString
import com.jueggs.utils.extension.setSimpleAdapter
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import javax.inject.Inject

class SearchCriteriaFragment : BaseFragment<SearchCriteriaView, SearchCriteriaViewModel>(), SearchCriteriaView {
    @Inject
    lateinit var presenter: ISearchCriteriaPresenter
    private var tags: List<String> = arrayListOf()

    override fun inject() = App.presenterComponent.inject(this)
    override fun presenter() = presenter as BasePresenter<SearchCriteriaView, SearchCriteriaViewModel>
    override fun self() = this
    override fun layout() = R.layout.fragment_search_criteria

    override fun viewModel() = SearchCriteriaViewModel.EMPTY

    override fun initializeViews(model: SearchCriteriaViewModel) {
        spnOrderBy.setSimpleAdapter(ORDER_ASC, ORDER_DESC)
        spnSortBy.setSimpleAdapter(SORT_CREATION, SORT_ACTIVITY, SORT_HOT, SORT_WEEK, SORT_MONTH, SORT_VOTES)
    }

    override fun setListeners() {
        ibtnSearch.setOnClickListener { presenter.onStartSearch() }
    }

    override fun getPageSize(): String = edtLimitTo.asString()
    override fun getSortOrder(): String = spnSortBy.asString()
    override fun getOrderType(): String = spnOrderBy.asString()
    override fun getMinScore(): String = edtScore.asString()
    override fun getTags(): List<String> = tags
}
