package com.jueggs.stackdownloader.ui.search.delegate

import android.view.View
import androidx.navigation.ui.setupWithNavController
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.view.SearchActivity
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.longToast

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchActivityDelegate : AppModeDelegate<SearchActivity>() {

    override fun initializeViewsInternal(): SearchActivity.() -> Unit = {
        botNavigation.setupWithNavController(navController)
    }

    override fun setListenersInternal(): SearchActivity.() -> Unit = {
        navController.addOnNavigatedListener { _, destination ->
            fabDownload.visibility = if (destination.id == R.id.searchResultFragment && !viewModel.isDataDownloaded && viewModel.isQuestionsDownloaded) View.VISIBLE else View.GONE
        }

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
                    navController.navigate(R.id.action_searchCriteriaFragment_to_searchResultFragment)
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