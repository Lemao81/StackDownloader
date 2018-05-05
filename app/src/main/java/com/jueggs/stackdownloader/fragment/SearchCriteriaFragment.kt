package com.jueggs.stackdownloader.fragment

import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.presenter.interfaces.ISearchCriteriaPresenter
import com.jueggs.stackdownloader.util.*
import com.jueggs.stackdownloader.view.*
import com.jueggs.utils.base.*
import com.jueggs.utils.extension.*
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class SearchCriteriaFragment : BaseFragment<SearchCriteriaView, SearchCriteriaViewModel>(), SearchCriteriaView {
    @Inject
    lateinit var presenter: ISearchCriteriaPresenter
    private var tags: List<String> = emptyList()

    override fun inject() = App.applicationComponent.inject(this)
    override fun presenter() = presenter as BasePresenter<SearchCriteriaView, SearchCriteriaViewModel>
    override fun self() = this
    override fun layout() = R.layout.fragment_search_criteria

    override fun viewModel() = SearchCriteriaViewModel.EMPTY

    override fun initializeViews(model: SearchCriteriaViewModel) {
        spnOrderBy.withSimpleAdapter(ORDER_ASC, ORDER_DESC)
        spnSortBy.withSimpleAdapter(SORT_CREATION, SORT_ACTIVITY, SORT_HOT, SORT_WEEK, SORT_MONTH, SORT_VOTES)
    }

    override fun setListeners() {
        ibtnSearch.onClick { presenter.onStartSearch() }
    }

    override fun getPageSize(): String = edtLimitTo.asString()
    override fun getSortOrder(): String = spnSortBy.asString()
    override fun getOrderType(): String = spnOrderBy.asString()
    override fun getMinScore(): String = edtScore.asString()
    override fun getTags(): List<String> = tags
}
