package com.jueggs.stackdownloader.ui.search.delegate

import com.jueggs.andutils.extension.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.view.*
import kotlinx.android.synthetic.main.activity_search.*

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchDelegate : AppModeDelegate<SearchActivity> {
    override fun setListeners(activity: SearchActivity) {
        activity.apply {
            viewModel.criteriaViewModel.onSearch.nonNull().observe(this) {
                addFragment(R.id.fragment, SearchResultFragment.newInstance())
                viewModel.checkedNavigationItem.value = R.id.menuItems
                toggleHomeAsUp(true)
            }
            viewModel.checkedNavigationItem.nonNull().observe(this) { botNavigation.checkItem(it) }
            botNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menuSearch -> {
                        replaceFragment(R.id.fragment, SearchCriteriaFragment.newInstance())
                        true
                    }
                    R.id.menuItems -> {
                        replaceFragment(R.id.fragment, SearchResultFragment.newInstance())
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onBackPressed(delegator: SearchActivity) {}
}