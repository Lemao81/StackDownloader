package com.jueggs.stackdownloader.ui.search.delegate

import androidx.navigation.ui.setupWithNavController
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.view.*
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.longToast

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchActivityDelegate : AppModeDelegate<SearchActivity>() {

    override fun initializeViewsInternal(): SearchActivity.() -> Unit = {
        botNavigation.setupWithNavController(navController)
    }

    override fun onInitialStartInternal(): SearchActivity.() -> Unit = {
        addFragment(R.id.navHostFragment, SearchResultFragment.newInstance(), false, SearchResultFragment.TAG)
        botNavigation.checkItem(R.id.searchResultFragment)
    }

    override fun setListenersInternal(): SearchActivity.() -> Unit = {
        botNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.searchCriteriaFragment -> {
                    navController.navigate(R.id.action_searchResultFragment_to_searchCriteriaFragment)
                    true
                }
                R.id.searchResultFragment -> {
                    navController.navigate(R.id.action_searchCriteriaFragment_to_searchResultFragment)
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
                    navController.navigate(R.id.action_searchResultFragment_to_searchCriteriaFragment)
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