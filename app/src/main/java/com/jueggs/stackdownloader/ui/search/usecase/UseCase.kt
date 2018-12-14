package com.jueggs.stackdownloader.ui.search.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class UseCase<in TRequest> {
    protected val data = MutableLiveData<UseCaseResult>()

    fun execute(request: TRequest): LiveData<UseCaseResult> {
        doExecute(request)
        return data
    }

    abstract fun doExecute(request: TRequest)

    object Request
}