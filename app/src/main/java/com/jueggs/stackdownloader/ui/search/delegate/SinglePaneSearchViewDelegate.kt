package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.andutils.extension.*
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.ui.search.view.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.defaultSharedPreferences

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
            viewModel.criteriaViewModel.onSearch.nonNull().observe(this) {
                //TODO lib
                activity.detachFragment(activity.findFragment(SearchCriteriaFragment.TAG!!))
                activity.attachOrAddFragment(R.id.fragment, lazy { SearchResultFragment.newInstance() }, false, SearchResultFragment.TAG)
                botNavigation.selectedItemId = R.id.menuItems
                toggleHomeAsUp(true)
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