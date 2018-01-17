package com.jueggs.stackdownloader.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.model.ItemShell
import com.jueggs.stackdownloader.model.Question
import com.jueggs.stackdownloader.model.QueryParameter
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.utils.*
import com.jueggs.utils.extensions.asString
import com.jueggs.utils.extensions.isNetworkConnected
import com.jueggs.utils.extensions.setSimpleAdapter
import com.jueggs.utils.logNetwork
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.support.v4.longToast
import javax.inject.Inject

class SearchCriteriaFragment : Fragment() {
    @Inject
    lateinit var _stackOverflowClient: StackOverflowClient

    private var _tags: List<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dagger().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_search_criteria, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeComponents()
        initializeListeners()
    }

    private fun initializeComponents() {
        if (context != null) {
            spnOrderBy.setSimpleAdapter(ORDER_ASC, ORDER_DESC)
            spnSortBy.setSimpleAdapter(SORT_CREATION, SORT_ACTIVITY, SORT_HOT, SORT_WEEK, SORT_MONTH, SORT_VOTES)
        }
    }

    private fun initializeListeners() {
        ibtnSearch.setOnClickListener(::onSearch)
    }

    private fun onSearch(view: View) {
        if (context != null && context!!.isNetworkConnected()) {

        } else longToast("No network connection available")
    }
}