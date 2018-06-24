package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.R.id.btnToSearchCriteria
import com.jueggs.stackdownloader.ui.search.view.SearchResultFragment
import com.jueggs.stackdownloader.util.navigateTo
import kotlinx.android.synthetic.main.search_result_empty_view.*

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchResultFragmentDelegate : AppModeDelegate<SearchResultFragment>() {

    override fun setListenersInternal(): SearchResultFragment.() -> Unit = {
        btnToSearchCriteria.navigateTo(R.id.action_searchResultFragment_to_searchCriteriaFragment)
    }
}