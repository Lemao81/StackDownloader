package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.domain.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteDataUseCase(private val repository: Repository) : UseCase<UseCase.Request>() {

    override fun doExecute(request: Request) {
        try {
            // TODO remove globalscope
            GlobalScope.launch {
                repository.deleteData()
                data.postValue(Complete)
            }
        } catch (exception: Exception) {
            data.postValue(Error(exception))
        }
    }
}