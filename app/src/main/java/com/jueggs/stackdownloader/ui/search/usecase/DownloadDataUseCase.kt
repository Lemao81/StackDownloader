package com.jueggs.stackdownloader.ui.search.usecase

import android.content.Context
import com.jueggs.andutils.extension.doWithNetworkConnection
import com.jueggs.andutils.extension.otherwise
import com.jueggs.domain.DataProvider
import com.jueggs.domain.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DownloadDataUseCase(
    private val context: Context,
    private val repository: Repository,
    private val dataProvider: DataProvider
)
    : UseCase<UseCase.Request>() {

    override fun doExecute(request: UseCase.Request) {
        try {
            context.doWithNetworkConnection {
                data.value = Loading

                // TODO remove globalscope
                GlobalScope.launch {
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