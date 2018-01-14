package com.jueggs.stackdownloader.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.models.Question

class SearchResultFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_search_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun setQuestions(questions: List<Question>) {

    }

    companion object {
        fun newInstance(): SearchResultFragment = SearchResultFragment()
    }
}