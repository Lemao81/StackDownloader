package com.jueggs.stackdownloader.ui.search.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.jueggs.andutils.extension.fire
import com.jueggs.data.repository.LiveRepository
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.ui.search.usecase.*
import org.joda.time.DateTime
import java.util.*

class SearchCriteriaViewModel(
        liveRepository: LiveRepository,
        private val searchUseCase: SearchUseCase,
        private val addTagUseCase: AddTagUseCase
) {
    val onEditFromDate: MutableLiveData<Unit> = MutableLiveData()
    val onEditToDate: MutableLiveData<Unit> = MutableLiveData()

    private val searchInput: MutableLiveData<SearchCriteria> = MutableLiveData()
    val searchResult: LiveData<UseCaseResult> = Transformations.switchMap(searchInput) { searchUseCase.execute(SearchRequest(it)) }

    private val addTagInput: MutableLiveData<CharSequence?> = MutableLiveData()
    val addTagResult: LiveData<UseCaseResult> = Transformations.switchMap(addTagInput) { addTagUseCase.execute(AddTagRequest(it, selectedTags.value)) }

    val selectedTags: MutableLiveData<MutableList<String>> = MutableLiveData()

    val availableTags: LiveData<List<String>> = liveRepository.getAllTagNames()
    val tag: MutableLiveData<CharSequence> = MutableLiveData()
    val orderType: MutableLiveData<Int> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val fromDate: ObservableField<Date> = ObservableField(Date())
    val toDate: ObservableField<Date> = ObservableField(Date())

    fun onAddTag() = addTagInput.postValue(tag.value)

    fun onRemoveTag(tagName: String) = selectedTags.postValue(selectedTags.value?.apply { remove(tagName) })

    fun onStartSearch() = searchInput.postValue(SearchCriteria(orderType.value, sortType.value, selectedTags.value, fromDate.get(), toDate.get()))

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