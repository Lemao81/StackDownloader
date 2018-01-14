package com.jueggs.stackdownloader.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.models.QuestionShell
import com.jueggs.stackdownloader.models.QuestionsQueryParameter
import com.jueggs.stackdownloader.retrofit.StackOverflowClient
import com.jueggs.stackdownloader.utils.RetrofitCallbackAdapter
import com.jueggs.stackdownloader.utils.asString
import com.jueggs.stackdownloader.utils.dagger
import com.jueggs.utils.extensions.asString
import com.jueggs.utils.extensions.isNetworkConnected
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.support.v4.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        initializeListeners()
    }

    private fun initializeListeners() {
        ibtnSearch.setOnClickListener(::onSearch)
    }

    private fun onSearch(view: View) {
        if (context != null && context!!.isNetworkConnected()) {
            val queryParams = QuestionsQueryParameter.Builder().pagesize(edtLimitTo.asString()).sort(spnOrderBy.asString()).tagged(_tags).build()
            val questionShellCall = _stackOverflowClient.questions(queryParams.build())

            questionShellCall.enqueue(RetrofitCallbackAdapter<QuestionShell>(context!!) { questionShell ->
                (fragmentManager!!.findFragmentById(R.id.fragSearchResult) as SearchResultFragment).setQuestions(questionShell.items)
            })
        } else longToast("No network connection available")
    }

    companion object {
        fun newInstance(): SearchCriteriaFragment = SearchCriteriaFragment()
    }
}