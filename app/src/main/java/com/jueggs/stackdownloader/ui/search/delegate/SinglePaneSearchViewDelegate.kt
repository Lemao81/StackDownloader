package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.view.*
import com.jueggs.stackdownloader.util.isDebug
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.*

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchViewDelegate : AppModeDelegate<SearchActivity> {
    override fun onInitialStart(activity: SearchActivity) {
        activity.apply {
            if (defaultSharedPreferences.getBoolean(PREFS_DATA_DOWNLOADED, false)) {
                addFragment(R.id.fragment, SearchResultFragment.newInstance(), false, SearchResultFragment.TAG)
                botNavigation.checkItem(R.id.menuItems)
            } else {
                addFragment(R.id.fragment, SearchCriteriaFragment.newInstance(), false, SearchCriteriaFragment.TAG)
                botNavigation.checkItem(R.id.menuSearch)
            }
        }
    }

    override fun setListeners(activity: SearchActivity) {
        activity.apply {
            viewModel.criteriaViewModel.searchResult.nonNull().observe(this) { result ->
                when (result) {
                    NoNetwork -> longToast(R.string.error_no_network)
                    Loading -> progress.visible()
                    Complete -> {
                        activity.detachFragment(activity.findFragment(SearchCriteriaFragment.TAG!!))
                        activity.attachOrAddFragment(R.id.fragment, lazy { SearchResultFragment.newInstance() }, false, SearchResultFragment.TAG)
                        botNavigation.checkItem(R.id.menuItems)
                        toggleHomeAsUp(true)

                        progress.gone()
                    }
                    is Error -> {
                        progress.gone()
                        longToast(R.string.error_search_failed)
                        if (AppMode.isDebug) throw result.throwable
                    }
                }
            }

            botNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menuSearch -> {
                        activity.detachFragment(activity.findFragment(SearchResultFragment.TAG!!))
                        activity.attachOrAddFragment(R.id.fragment, lazy { SearchCriteriaFragment.newInstance() }, false, SearchCriteriaFragment.TAG)
                        true
                    }
                    R.id.menuItems -> {
                        activity.detachFragment(activity.findFragment(SearchCriteriaFragment.TAG!!))
                        activity.attachOrAddFragment(R.id.fragment, lazy { SearchResultFragment.newInstance() }, false, SearchResultFragment.TAG)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onBackPressed(delegator: SearchActivity) {}
}