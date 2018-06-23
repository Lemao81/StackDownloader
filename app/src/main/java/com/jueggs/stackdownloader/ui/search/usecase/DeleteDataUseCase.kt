package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.domain.Repository
import kotlinx.coroutines.experimental.launch

class DeleteDataUseCase(private val repository: Repository) : UseCase<UseCase.Request>() {

    override fun doExecute(request: Request) {
        try {
            launch {
                repository.deleteData()
                data.postValue(Complete)
            }
        } catch (exception: Exception) {
            data.postValue(Error(exception))
        }
    }
}