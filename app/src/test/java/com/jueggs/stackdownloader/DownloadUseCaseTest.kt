package com.jueggs.stackdownloader

import android.content.Context
import com.jueggs.domain.DataProvider
import com.jueggs.domain.Repository
import com.jueggs.domain.model.Answer
import com.jueggs.jutils.Util.givenSuspended
import com.jueggs.stackdownloader.ui.search.usecase.Complete
import com.jueggs.stackdownloader.ui.search.usecase.DownloadDataUseCase
import com.jueggs.stackdownloader.ui.search.usecase.Error
import com.jueggs.stackdownloader.ui.search.usecase.UseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.willThrow
import com.nhaarman.mockito_kotlin.willReturn
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DownloadUseCaseTest {
    private val questionIds = listOf(1L, 2L, 3L)
    private val answers = listOf(Answer(id = 1), Answer(id = 2))

    private lateinit var contextMock: Context
    private lateinit var repoMock: Repository
    private lateinit var providerMock: DataProvider
    private lateinit var useCase: DownloadDataUseCase

    @Before
    fun setup() {
        contextMock = mock()
        repoMock = mock()
        providerMock = mock()
        useCase = DownloadDataUseCase(contextMock, repoMock, providerMock)
    }

    @Test
    fun `test that exception getting questions is returned`() {
        runBlocking {
            // given
            val exception = RuntimeException()
            given { repoMock.getAllQuestionIds() }.willThrow(exception)

            // act
            val liveData = useCase.execute(UseCase.Request)

            // assert
            verifyZeroInteractions(providerMock)
            assertTrue(liveData.value is Error && (liveData.value as Error).throwable == exception)
        }
    }

    @Test
    fun `test that exception fetching answers is returned`() {
        runBlocking {
            // given
            val exception = RuntimeException()
            given { repoMock.getAllQuestionIds() }.willReturn(questionIds)
            givenSuspended { providerMock.fetchAnswers(questionIds) }.willThrow(exception)

            // act
            val liveData = useCase.execute(UseCase.Request)

            // assert
            verify(repoMock).getAllQuestionIds()
            verifyNoMoreInteractions(repoMock)
            assertTrue(liveData.value is Error && (liveData.value as Error).throwable == exception)
        }
    }

    @Test
    fun `test that exception adding answers is returned`() {
        runBlocking {
            // given
            val exception = RuntimeException()
            given { repoMock.getAllQuestionIds() }.willReturn(questionIds)
            givenSuspended { providerMock.fetchAnswers(questionIds) }.willReturn(Single.just(answers))
            given { repoMock.addAnswers(any()) }.willThrow(exception)

            // act
            val liveData = useCase.execute(UseCase.Request)

            // assert
            then(repoMock).should(times(1)).addAnswers(answers)
            assertTrue(liveData.value is Error && (liveData.value as Error).throwable == exception)
        }
    }

    @Test
    fun `test that answers are persisted and success returned`() {
        runBlocking {
            // given
            given { repoMock.getAllQuestionIds() }.willReturn(questionIds)
            givenSuspended { providerMock.fetchAnswers(questionIds) }.willReturn(Single.just(answers))

            // act
            val liveData = useCase.execute(UseCase.Request)

            // assert
            then(repoMock).should(times(1)).addAnswers(answers)
            assertTrue(liveData.value is Complete)
        }
    }
}