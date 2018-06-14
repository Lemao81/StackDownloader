package com.jueggs.stackdownloader.ui.search.usecase

import android.content.Context
import com.jueggs.andutils.extension.doWithNetworkConnection
import com.jueggs.andutils.util.AppMode
import com.jueggs.domain.*
import com.jueggs.stackdownloader.util.isDebug

class InitialStartUseCase(private val context: Context, private val repository: Repository, private val dataProvider: DataProvider) : UseCase<InitialStartRequest>() {

    override fun doExecute(request: InitialStartRequest) {
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

object InitialStartRequest