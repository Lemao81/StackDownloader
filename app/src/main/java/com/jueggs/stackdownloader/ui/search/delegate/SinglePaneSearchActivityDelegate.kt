package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.view.*
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.longToast

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchActivityDelegate : AppModeDelegate<SearchActivity> {

    override fun onInitialStart(activity: SearchActivity) {
        activity.apply {
            addFragment(R.id.fragment, SearchResultFragment.newInstance(), false, SearchResultFragment.TAG)
            botNavigation.checkItem(R.id.menuItems)
        }
    }

    override fun setListeners(activity: SearchActivity) {
        activity.apply {
            botNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menuSearch -> {
                        detachFragment(findFragment(SearchResultFragment.TAG!!))
                        attachOrAddFragment(R.id.fragment, lazy { SearchCriteriaFragment.newInstance() }, false, SearchCriteriaFragment.TAG)
                        true
                    }
                    R.id.menuItems -> {
                        detachFragment(findFragment(SearchCriteriaFragment.TAG!!))
                        attachOrAddFragment(R.id.fragment, lazy { SearchResultFragment.newInstance() }, false, SearchResultFragment.TAG)
                        true
                    }
                    else -> false
                }
            }
            viewModel.getSearchResult().observeNonNull(this) { result ->
                when (result) {
                    NoNetwork -> longToast(R.string.error_no_network)
                    Loading -> progress.visible()
                    Complete -> {
                        progress.gone()
                        detachFragment(findFragment(SearchCriteriaFragment.TAG!!))
                        attachOrAddFragment(R.id.fragment, lazy { SearchResultFragment.newInstance() }, false, SearchResultFragment.TAG)
                        botNavigation.checkItem(R.id.menuItems)
                        toggleHomeAsUp(true)
                    }
                    is Error -> {
                        progress.gone()
                        longToast(R.string.error_search_failed)
                        if (AppMode.isDebug) throw result.throwable
                    }
                }
            }
        }
    }

    override fun onBackPressed(delegator: SearchActivity) {}
}