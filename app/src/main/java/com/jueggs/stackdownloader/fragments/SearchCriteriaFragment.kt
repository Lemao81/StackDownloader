package com.jueggs.stackdownloader.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jueggs.stackdownloader.R

class SearchCriteriaFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_search_criteria, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(): SearchCriteriaFragment = SearchCriteriaFragment()
    }
}