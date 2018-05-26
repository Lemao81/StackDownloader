package com.jueggs.data

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jueggs.data.dao.*
import org.junit.*
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
    fun test_that_all_answers_of_question_are_retrieved() {
        //TODO
    }

    @Test
    fun test_that_all_answers_are_deleted() {
        //TODO
    }
}