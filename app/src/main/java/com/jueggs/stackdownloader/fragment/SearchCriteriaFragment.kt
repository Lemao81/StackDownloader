package com.jueggs.stackdownloader.fragment

import android.widget.Toast
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.presenter.SearchCriteriaPresenter
import com.jueggs.stackdownloader.util.*
import com.jueggs.stackdownloader.view.SearchCriteriaView
import com.jueggs.utils.base.BaseFragment
import com.jueggs.utils.extension.asString
import com.jueggs.utils.extension.setSimpleAdapter
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class SearchCriteriaFragment : BaseFragment<SearchCriteriaView>(), SearchCriteriaView {
    @Inject
    lateinit var presenter: SearchCriteriaPresenter
    @Inject
    lateinit var app: App
    private var tags: List<String> = arrayListOf()

    override fun inject() = app.applicationComponent.inject(this)
    override fun presenter() = presenter
    override fun self() = this
    override fun layout() = R.layout.fragment_search_criteria

    override fun initializeViews() {
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

    override fun longToast(msg: String): Toast = ctx.longToast(msg)
    override fun longToast(resId: Int): Toast = ctx.longToast(resId)
}
