package com.jueggs.stackdownloader.ui.search.delegate

import androidx.navigation.ui.setupWithNavController
import com.jueggs.andutils.extension.gone
import com.jueggs.andutils.extension.nonNull
import com.jueggs.andutils.extension.observe
import com.jueggs.andutils.extension.visible
import com.jueggs.andutils.util.AppMode
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.ui.search.usecase.Complete
import com.jueggs.stackdownloader.ui.search.usecase.Loading
import com.jueggs.stackdownloader.ui.search.usecase.NoNetwork
import com.jueggs.stackdownloader.ui.search.usecase.Error
import com.jueggs.stackdownloader.ui.search.view.SearchActivity
import com.jueggs.stackdownloader.util.isDebug
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.longToast

@Suppress("PLUGIN_WARNING")
class SinglePaneSearchActivityDelegate : AppModeDelegate<SearchActivity>() {

    override fun initializeViewsInternal(): SearchActivity.() -> Unit = {
        navController?.let { botNavigation.setupWithNavController(it) }
    }

    override fun setListenersInternal(): SearchActivity.() -> Unit = {
        navController?.addOnNavigatedListener { _, destination ->
            if (destination.id == R.id.searchResultFragment && !viewModel.isDataDownloaded && viewModel.isQuestionsDownloaded)
                fabDownload.show()
            else
                fabDownload.hide()
        }

        botNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.searchCriteriaFragment -> {
                    navController?.navigate(R.id.action_searchResultFragment_to_searchCriteriaFragment)
                    true
                }
                R.id.searchResultFragment -> {
                    navController?.navigate(R.id.action_searchCriteriaFragment_to_searchResultFragment)
                    true
                }
                else -> false
            }
        }

        viewModel.searchResult.nonNull().observe(this) { result ->
            when (result) {
                NoNetwork -> longToast(R.string.error_no_network)
                Loading -> progress.visible()
                Complete -> {
                    progress.gone()
                    navController?.navigate(R.id.action_searchCriteriaFragment_to_searchResultFragment)
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