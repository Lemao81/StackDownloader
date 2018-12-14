package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.view.SearchResultFragment
import com.jueggs.stackdownloader.util.navigateOnClick
import kotlinx.android.synthetic.main.search_result_empty_view.*

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchResultFragmentDelegate : AppModeDelegate<SearchResultFragment>() {

    override fun setListenersInternal(): SearchResultFragment.() -> Unit = {
        btnToSearchCriteria.navigateOnClick(R.id.action_searchResultFragment_to_searchCriteriaFragment)
    }
}