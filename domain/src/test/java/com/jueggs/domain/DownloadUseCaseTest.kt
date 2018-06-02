package com.jueggs.domain

import com.jueggs.domain.model.*
import com.jueggs.domain.usecase.DownloadUseCase
import com.jueggs.jutils.*
import com.jueggs.jutils.extension.*
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.*
import org.mockito.Mockito.mock

class DownloadUseCaseTest {
    private val questionIds = listOf(1L, 2L, 3L)
    private val answers = listOf(Answer(id = 1), Answer(id = 2))

    private lateinit var repoMock: Repository
    private lateinit var providerMock: DataProvider
    private lateinit var useCase: DownloadUseCase

    @Before
    fun setup() {
        repoMock = mock(Repository::class.java)
        providerMock = mock(DataProvider::class.java)
        useCase = DownloadUseCase(repoMock, providerMock)
    }

    @Test
    fun `test that exception getting questions is returned`() {
        // given
        val exception = RuntimeException()
        given { repoMock.getAllQuestionIds() } willThrow exception

        // act
        val testSubscriber = useCase.execute().deferredResult.test().awaitDone(3, SECONDS)

        // assert
        verifyZeroInteractions(providerMock)
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue { it is Failure && it.exception == exception }
    }

    @Test
    fun `test that exception fetching answers is returned`() {
        // given
        val exception = RuntimeException()
        given { repoMock.getAllQuestionIds() } willReturn questionIds
        givenSuspended { providerMock.fetchAnswers(questionIds) } willThrow exception

        // act
        val testSubscriber = useCase.execute().deferredResult.test().awaitDone(3, SECONDS)

        // assert
        verify(repoMock).getAllQuestionIds()
        verifyNoMoreInteractions(repoMock)
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue { it is Failure && it.exception == exception }
    }

    @Test
    fun `test that exception adding answers is returned`() {
        // given
        val exception = RuntimeException()
        given { repoMock.getAllQuestionIds() } willReturn questionIds
        givenSuspended { providerMock.fetchAnswers(questionIds) } willReturn Single.just(answers)
        given { repoMock.addAnswers(any()) } willThrow exception

        // act
        val testSubscriber = useCase.execute().deferredResult.test().awaitDone(3, SECONDS)

        // assert
        then(repoMock).should(times(1)).addAnswers(answers)
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue { it is Failure && it.exception == exception }
    }

    @Test
    fun `test that answers are persisted and success returned`() {
        // given
        given { repoMock.getAllQuestionIds() } willReturn questionIds
        givenSuspended { providerMock.fetchAnswers(questionIds) } willReturn Single.just(answers)

        // act
        val testSubscriber = useCase.execute().deferredResult.test().awaitDone(3, SECONDS)

        // assert
        then(repoMock).should(times(1)).addAnswers(answers)
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue { it === Success }
    }
}