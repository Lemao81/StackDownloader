package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.jueggs.andutils.extension.fire
import com.jueggs.domain.Repository
import com.jueggs.domain.model.Tag
import com.jueggs.domain.usecase.AddTagUseCase
import com.jueggs.stackdownloader.ui.search.usecase.*
import java.util.*

class SearchCriteriaViewModel(
        private val searchViewModel: SearchViewModel,
        repository: Repository,
        private val addTagUseCase: AddTagUseCase,
        private val startSearchUseCase: StartSearchUseCase,
        private val setPeriodUseCase: SetPeriodUseCase
) {
    var availableTags: LiveData<List<Tag>> = repository.getAllTagsLive()
    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()
    val tag: MutableLiveData<CharSequence> = MutableLiveData()
    val orderType: MutableLiveData<Int> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val fromDate: ObservableField<Date> = ObservableField(Date())
    val toDate: ObservableField<Date> = ObservableField(Date())

    val onSearch: MutableLiveData<Unit> = MutableLiveData()
    val onEditFromDate: MutableLiveData<Unit> = MutableLiveData()
    val onEditToDate: MutableLiveData<Unit> = MutableLiveData()

    fun onAddTag() = addTagUseCase.add(this)

    fun onRemoveTag(tagName: String) = addTagUseCase.remove(this, tagName)

    fun onStartSearch() = startSearchUseCase.go(this)

    fun onEditFromDate() = onEditFromDate.fire()

    fun onEditToDate() = onEditToDate.fire()

    fun onToday() = setPeriodUseCase.today(this)

    fun onLastWeek() = setPeriodUseCase.lastWeek(this)

    fun onLastMonth() = setPeriodUseCase.lastMonth(this)
}