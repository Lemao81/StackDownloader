package com.jueggs.domain

import com.jueggs.domain.model.*
import com.jueggs.jutils.extension.*
import com.jueggs.jutils.givenSuspended
import com.jueggs.stackdownloader.ui.search.usecase.DownloadDataUseCase
import com.nhaarman.mockito_kotlin.*
import com.sun.net.httpserver.Authenticator
import io.reactivex.Single
import kotlinx.coroutines.experimental.runBlocking
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.runner.notification.Failure
import org.mockito.Mockito.mock

class DownloadUseCaseTest {
    private val questionIds = listOf(1L, 2L, 3L)
    private val answers = listOf(Answer(id = 1), Answer(id = 2))

    private lateinit var repoMock: Repository
    private lateinit var providerMock: DataProvider
    private lateinit var useCase: DownloadDataUseCase

    @Before
    fun setup() {
        repoMock = mock(Repository::class.java)
        providerMock = mock(DataProvider::class.java)
        useCase = DownloadUseCase(repoMock, providerMock)
    }

    @Test
    fun `test that exception getting questions is returned`() {
        runBlocking {
            // given
            val exception = RuntimeException()
            given { repoMock.getAllQuestionIds() } willThrow exception

            // act
            val deferred = useCase.execute().deferredResult.await()

            // assert
            verifyZeroInteractions(providerMock)
            assertTrue(deferred is Failure && deferred.exception == exception)
        }
    }

    @Test
    fun `test that exception fetching answers is returned`() {
        runBlocking {
            // given
            val exception = RuntimeException()
            given { repoMock.getAllQuestionIds() } willReturn questionIds
            givenSuspended { providerMock.fetchAnswers(questionIds) } willThrow exception

            // act
            val deferred = useCase.execute().deferredResult.await()

            // assert
            verify(repoMock).getAllQuestionIds()
            verifyNoMoreInteractions(repoMock)
            assertTrue(deferred is Failure && deferred.exception == exception)
        }
    }

    @Test
    fun `test that exception adding answers is returned`() {
        runBlocking {
            // given
            val exception = RuntimeException()
            given { repoMock.getAllQuestionIds() } willReturn questionIds
            givenSuspended { providerMock.fetchAnswers(questionIds) } willReturn Single.just(answers)
            given { repoMock.addAnswers(any()) } willThrow exception

            // act
            val deferred = useCase.execute().deferredResult.await()

            // assert
            then(repoMock).should(times(1)).addAnswers(answers)
            assertTrue(deferred is Failure && deferred.exception == exception)
        }
    }

    @Test
    fun `test that answers are persisted and success returned`() {
        runBlocking {
            // given
            given { repoMock.getAllQuestionIds() } willReturn questionIds
            givenSuspended { providerMock.fetchAnswers(questionIds) } willReturn Single.just(answers)

            // act
            val deferred = useCase.execute().deferredResult.await()

            // assert
            then(repoMock).should(times(1)).addAnswers(answers)
            assertTrue(deferred is Authenticator.Success)
        }
    }
}