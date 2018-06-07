package com.jueggs.stackdownloader.ui.search.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.data.repository.LiveRepository
import com.jueggs.domain.model.*
import com.jueggs.domain.usecase.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.util.isDebug
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.joda.time.DateTime
import java.util.*

class SearchCriteriaViewModel(
        private val application: Application,
        liveRepository: LiveRepository,
        private val addTagUseCase: AddTagUseCase,
        private val searchUseCase: SearchUseCase
) {
    val onLongToast: MutableLiveData<Int> = MutableLiveData()
    val onShowProgress: MutableLiveData<Boolean> = MutableLiveData()
    val onHideKeyboard: MutableLiveData<Unit> = MutableLiveData()
    val onSearch: MutableLiveData<Unit> = MutableLiveData()
    val onEditFromDate: MutableLiveData<Unit> = MutableLiveData()
    val onEditToDate: MutableLiveData<Unit> = MutableLiveData()

    val _selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    val selectedTags: LiveData<MutableList<String>>
        get() = _selectedTags

    val availableTags: LiveData<List<String>> = liveRepository.getAllTagNames()
    val tag: MutableLiveData<CharSequence> = MutableLiveData()
    val orderType: MutableLiveData<Int> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val fromDate: ObservableField<Date> = ObservableField(Date())
    val toDate: ObservableField<Date> = ObservableField(Date())

    fun onAddTag() {
        launch(UI) {
            val request = AddTagRequest(tag.value, _selectedTags.value)
            val result = withContext(CommonPool) {
                addTagUseCase.execute(request).deferredResult.await()
            }

            when (result) {
                EmptyInput -> onLongToast.fire(R.string.error_no_tag)
                TagAlreadyAdded -> onLongToast.fire(R.string.error_tag_already_added)
                TagNotAvailable -> onLongToast.fire(R.string.error_nonexistent_tag)
                is AddTagFailure -> {
                    onLongToast.fire(R.string.error_add_tag_failed)
                    if (AppMode.isDebug)
                        throw result.exception
                }
                is TagAdded -> {
                    _selectedTags.value = result.tags
                    tag.value = ""
                    onHideKeyboard.fire()
                }
            }
        }
    }

    fun onRemoveTag(tagName: String) {
        _selectedTags.value = _selectedTags.value?.apply { remove(tagName) }
    }

    fun onStartSearch() = application.doWithNetworkConnection {
        onShowProgress.fireTrue()
        launch(UI) {
            val criteria = SearchCriteria(orderType.value, sortType.value, _selectedTags.value, fromDate.get(), toDate.get())

            val result = withContext(CommonPool) {
                searchUseCase.execute(SearchRequest(criteria)).deferredResult.await()
            }

            when (result) {
                Success -> onSearch.fire()
                is Failure -> {
                    onLongToast.fire(R.string.error_search_failed)
                    if (AppMode.isDebug)
                        throw result.exception
                }
            }
        }
    } otherwise { onLongToast.fire(R.string.error_no_network) }

    fun onEditFromDate() = onEditFromDate.fire()

    fun onEditToDate() = onEditToDate.fire()

    fun onToday() {
        fromDate.set(Date())
        toDate.set(Date())
    }

    fun onLastWeek() {
        fromDate.set(DateTime().minusWeeks(1).toDate())
        toDate.set(Date())
    }

    fun onLastMonth() {
        fromDate.set(DateTime().minusMonths(1).toDate())
        toDate.set(Date())
    }
}