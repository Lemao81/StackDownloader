package com.jueggs.stackdownloader.ui.search.usecase

import android.content.Context
import com.jueggs.andutils.extension.*
import com.jueggs.domain.*
import kotlinx.coroutines.experimental.launch

class DownloadUseCase(private val context: Context, private val repository: Repository, private val dataProvider: DataProvider) : UseCase<DownloadRequest>() {

    override fun doExecute(request: DownloadRequest) {
        try {
            context.doWithNetworkConnection {
                data.value = Loading

                launch {
                    val questionIds = repository.getAllQuestionIds()
                    dataProvider.fetchAnswers(questionIds).subscribe({
                        repository.addAnswers(it)
                        data.postValue(Complete)
                    }, {
                        data.postValue(Error(it))
                    })
                }
            } otherwise {
                data.value = NoNetwork
            }
        } catch (exception: Exception) {
            data.value = Error(exception)
        }
    }
}

object DownloadRequest