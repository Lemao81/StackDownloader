package com.jueggs.data

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jueggs.data.dao.*
import org.hamcrest.core.IsEqual.equalTo
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnswerDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var answerDao: AnswerDao
    private lateinit var questionDao: QuestionDao

    @Before
    fun setup() {
        database = AppDatabase.getInMemoryInstance(InstrumentationRegistry.getContext())
        answerDao = database.answerDao()
        questionDao = database.questionDao()
    }

    @After
    fun tearDown() = database.close()

    @Test
    fun test_that_all_answers_are_inserted_and_retrieved() {
        val answers = TestUtils.createAnswers(3)
        answerDao.insertAll(answers)
        val allAnswers = answerDao.getAll()

        assertThat(allAnswers.size, equalTo(answers.size))
    }

    @Test
    fun test_that_all_answers_of_question_are_retrieved() {
        val questions = TestUtils.createQuestions(2)
        questionDao.insertAll(questions)

        val answers = TestUtils.createAnswers(5)
        answers[0].questionId = questions[0].id
        answers[1].questionId = questions[0].id
        answers[2].questionId = questions[1].id
        answers[3].questionId = questions[1].id
        answers[4].questionId = questions[1].id
        answerDao.insertAll(answers)

        val answersOfQuestion1 = answerDao.getAnswersOfQuestion(questions[0].id)
        val answersOfQuestion2 = answerDao.getAnswersOfQuestion(questions[1].id)

        assertThat(answersOfQuestion1.size, equalTo(2))
        assertThat(answersOfQuestion2.size, equalTo(3))
        assertThat(answersOfQuestion1[0].id, equalTo(answers[0].id))
        assertThat(answersOfQuestion1[1].id, equalTo(answers[1].id))
        assertThat(answersOfQuestion2[0].id, equalTo(answers[2].id))
        assertThat(answersOfQuestion2[1].id, equalTo(answers[3].id))
        assertThat(answersOfQuestion2[2].id, equalTo(answers[4].id))
    }

    @Test
    fun test_that_all_answers_are_deleted() {
        val answers = TestUtils.createAnswers(3)
        answerDao.insertAll(answers)
        var allAnswers = answerDao.getAll()

        assertTrue(allAnswers.isNotEmpty())

        answerDao.deleteAll()
        allAnswers = answerDao.getAll()

        assertTrue(allAnswers.isEmpty())
    }
}