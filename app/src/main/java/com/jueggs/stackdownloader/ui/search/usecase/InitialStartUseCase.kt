package com.jueggs.stackdownloader.ui.search.usecase

import android.content.Context
import com.jueggs.andutils.extension.doWithNetworkConnection
import com.jueggs.andutils.util.AppMode
import com.jueggs.domain.DataProvider
import com.jueggs.domain.Repository
import com.jueggs.stackdownloader.util.isDebug

class InitialStartUseCase(private val context: Context, private val repository: Repository, private val dataProvider: DataProvider) : UseCase<UseCase.Request>() {

    override fun doExecute(request: UseCase.Request) {
        try {
            context.doWithNetworkConnection {
                dataProvider.fetchTags().subscribe { tags ->
                    repository.addTags(tags)
                }
            }
        } catch (exception: Exception) {
            if (AppMode.isDebug) throw exception
        }
    }
}