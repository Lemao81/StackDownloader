package com.jueggs.stackdownloader.ui.search.view

import com.jueggs.andutils.base.BaseFragment
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.customview.stackoverflowtag.StackoverflowTag
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R.id.autoTxtTags
import com.jueggs.stackdownloader.R.id.linTagContainer
import com.jueggs.stackdownloader.ui.search.usecase.*
import com.jueggs.stackdownloader.ui.search.viewmodel.SearchViewModel
import com.jueggs.stackdownloader.util.*
import kotlinx.android.synthetic.main.fragment_search_criteria.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.architecture.ext.sharedViewModel

class SearchCriteriaFragment : BaseFragment<SearchCriteriaFragment.Listener>() {
    val viewModel by sharedViewModel<SearchViewModel>()

    override fun layout() = R.layout.fragment_search_criteria
    override fun bindingItems() = mapOf(BR.model to viewModel)
    override fun toolbarTitle() = R.string.title_search_criteria

    override fun onInitialStart() = viewModel.onInitialStart()

    override fun setListeners() {
        viewModel.availableTags.observeNonNull(this) { autoTxtTags.withSimpleAdapter(it) }
        viewModel.selectedTags.observeNonNull(this) { tags ->
            context?.let {
                linTagContainer.removeAllViews()
                tags.forEach { tag ->
                    val tagView = StackoverflowTag(it, tag, 7)
                    tagView.onClick { viewModel.onRemoveTag(tagView.tagName) }
                    linTagContainer.addView(tagView)
                }
            }
        }
        viewModel.getAddTagResult().observeNonNull(this) { result ->
            when (result) {
                EmptyInput -> longToast(R.string.error_no_tag)
                TagAlreadyAdded -> longToast(R.string.error_tag_already_added)
                TagNotAvailable -> longToast(R.string.error_nonexistent_tag)
                is TagAdded -> {
                    activity?.hideKeyboard()
                    viewModel.selectedTags.postValue(result.tags)
                    viewModel.tag.postValue("")
                }
                is Error -> {
                    longToast(R.string.error_add_tag_failed)
                    if (AppMode.isDebug) throw result.throwable
                }
            }
        }
    }

    companion object {
        val TAG = SearchCriteriaFragment::class.simpleName

        fun newInstance() = SearchCriteriaFragment()
    }

    interface Listener
}