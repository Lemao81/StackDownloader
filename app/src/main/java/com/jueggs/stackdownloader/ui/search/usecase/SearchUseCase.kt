package com.jueggs.stackdownloader.ui.search.usecase

import android.content.Context
import com.jueggs.andutils.extension.*
import com.jueggs.domain.*
import com.jueggs.domain.model.SearchCriteria

class SearchUseCase(private val context: Context, private val repository: Repository, private val dataProvider: DataProvider) : UseCase<SearchRequest>() {

    override fun doExecute(request: SearchRequest) {
        try {
            context.doWithNetworkConnection {
                data.value = Loading

                dataProvider.fetchQuestions(request.criteria).subscribe({
                    repository.replaceQuestions(it)
                    data.postValue(Complete)
                }, {
                    data.postValue(Error(it))
                })
            } otherwise {
                data.postValue(NoNetwork)
            }
        } catch (exception: Exception) {
            data.postValue(Error(exception))
        }
    }
}

data class SearchRequest(val criteria: SearchCriteria)