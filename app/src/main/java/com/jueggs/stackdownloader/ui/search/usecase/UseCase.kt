package com.jueggs.stackdownloader.ui.search.usecase

import android.arch.lifecycle.*

abstract class UseCase<in TRequest> {
    protected val data = MutableLiveData<UseCaseResult>()

    fun execute(request: TRequest): LiveData<UseCaseResult> {
        doExecute(request)
        return data
    }

    abstract fun doExecute(request: TRequest)
}