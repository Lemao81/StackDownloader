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
    private lateinit var ownerDao: OwnerDao

    @Before
    fun setup() {
        database = AppDatabase.getInMemoryInstance(InstrumentationRegistry.getContext())
        answerDao = database.answerDao()
        questionDao = database.questionDao()
        ownerDao = database.ownerDao()
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
    fun test_that_all_answers_of_question_including_owner_are_retrieved() {
        val questions = TestUtils.createQuestions(2)
        questionDao.insertAll(questions)

        val answers = TestUtils.createAnswers(5)
        answers[0].questionId = questions[0].id
        answers[1].questionId = questions[0].id
        answers[2].questionId = questions[1].id
        answers[3].questionId = questions[1].id
        answers[4].questionId = questions[1].id

        val owner = TestUtils.createOwners(5)
        answers[0].ownerId = owner[0].id ?: 1
        answers[1].ownerId = owner[1].id ?: 2
        answers[2].ownerId = owner[2].id ?: 3
        answers[3].ownerId = owner[3].id ?: 4
        answers[4].ownerId = owner[4].id ?: 5

        answerDao.insertAll(answers)
        ownerDao.insertAll(owner)

        val answersOfQuestion1 = answerDao.getAnswersOfQuestionIncludingOwner(questions[0].id)
        val answersOfQuestion2 = answerDao.getAnswersOfQuestionIncludingOwner(questions[1].id)

        assertThat(answersOfQuestion1.size, equalTo(2))
        assertThat(answersOfQuestion2.size, equalTo(3))
        assertThat(answersOfQuestion1[0].answer.id, equalTo(answers[0].id))
        assertThat(answersOfQuestion1[1].answer.id, equalTo(answers[1].id))
        assertThat(answersOfQuestion2[0].answer.id, equalTo(answers[2].id))
        assertThat(answersOfQuestion2[1].answer.id, equalTo(answers[3].id))
        assertThat(answersOfQuestion2[2].answer.id, equalTo(answers[4].id))
        assertThat(answersOfQuestion1[0].owner.id, equalTo(owner[0].id))
        assertThat(answersOfQuestion1[1].owner.id, equalTo(owner[1].id))
        assertThat(answersOfQuestion2[0].owner.id, equalTo(owner[2].id))
        assertThat(answersOfQuestion2[1].owner.id, equalTo(owner[3].id))
        assertThat(answersOfQuestion2[2].owner.id, equalTo(owner[4].id))
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

    @Test
    fun test_that_question_delete_cascades_answer_delete() {
        val questions = TestUtils.createQuestions(2)
        questionDao.insertAll(questions)

        val answers = TestUtils.createAnswers(5)
        answers[0].questionId = questions[0].id
        answers[1].questionId = questions[0].id
        answers[2].questionId = questions[1].id
        answers[3].questionId = questions[1].id
        answers[4].questionId = questions[1].id
        answerDao.insertAll(answers)

        var allAnswers = answerDao.getAll()

        assertTrue(allAnswers.isNotEmpty())

        questionDao.deleteAll()
        allAnswers = answerDao.getAll()

        assertTrue(allAnswers.isEmpty())
    }
}