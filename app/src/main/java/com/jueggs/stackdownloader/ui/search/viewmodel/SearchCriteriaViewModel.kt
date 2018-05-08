package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import android.content.Context
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.data.StackRepository
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.R

class SearchCriteriaViewModel(context: Context, private val repository: StackRepository) : AndroidViewModel(context.applicationContext as App) {
    val tags: MutableLiveData<MutableList<String>> = MutableLiveData()
    val errors: MutableLiveData<Int> = MutableLiveData()
    val search: MutableLiveData<SearchCriteria> = MutableLiveData()

    var limitTo: String = ""
    var score: String = ""
    var tag: String = ""
    var orderType: Int = 0
    var sortType: Int = 0

    fun onAddTag() {
        repository.getTags().subscribe { availableTags ->
            if (availableTags.map { it.name }.contains(tag))
                tags.value = tags.value?.apply { add(tag) }
            else
                errors.value = R.string.error_nonexistent_tag
        }
    }

    fun onStartSearch() {
        if (getApplication<App>().isNetworkConnected()) {
            search.value = SearchCriteria(limitTo, score, orderType, sortType, tags.value)
        } else
            errors.value = R.string.toast_no_network
    }
}