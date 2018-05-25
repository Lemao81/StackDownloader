package com.jueggs.data

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jueggs.data.dao.*
import com.jueggs.data.entity.QuestionTagJoinEntity
import org.hamcrest.core.IsEqual.equalTo
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var tagDao: TagDao
    private lateinit var questionDao: QuestionDao
    private lateinit var questionTagJoinDao: QuestionTagJoinDao

    @Before
    fun setup() {
        database = AppDatabase.getInMemoryInstance(InstrumentationRegistry.getContext())
        questionDao = database.questionDao()
        tagDao = database.tagDao()
        questionTagJoinDao = database.questionTagJoinDao()
    }

    @After
    fun tearDown() = database.close()

    @Test
    fun test_that_questions_are_inserted_and_retrieved() {
        val questions = TestUtils.createQuestions(3)
        questionDao.insertAll(questions)
        val allQuestions = questionDao.getAll()

        assertThat(allQuestions.size, equalTo(questions.size))
    }

    @Test
    fun test_that_questions_are_retrieved_including_tags() {
        val tags = TestUtils.createTags(5)
        val questions = TestUtils.createQuestions(2)

        tagDao.insertAll(tags)
        questionDao.insertAll(questions)

        questionTagJoinDao.insertAll(listOf(
                QuestionTagJoinEntity(questions[0].id, tags[0].name),
                QuestionTagJoinEntity(questions[0].id, tags[1].name),
                QuestionTagJoinEntity(questions[1].id, tags[2].name),
                QuestionTagJoinEntity(questions[1].id, tags[3].name),
                QuestionTagJoinEntity(questions[1].id, tags[4].name)
        ))

        val questionsIncludingTags = questionDao.getAllIncludingTags()

        assertThat(questionsIncludingTags.size, equalTo(questions.size))
        assertThat(questionsIncludingTags[0].tags.size, equalTo(2))
        assertThat(questionsIncludingTags[1].tags.size, equalTo(3))
        assertTrue(questionsIncludingTags[0].tags.map { it.tagName }.contains(tags[0].name))
        assertTrue(questionsIncludingTags[0].tags.map { it.tagName }.contains(tags[1].name))
        assertTrue(questionsIncludingTags[1].tags.map { it.tagName }.contains(tags[2].name))
        assertTrue(questionsIncludingTags[1].tags.map { it.tagName }.contains(tags[3].name))
        assertTrue(questionsIncludingTags[1].tags.map { it.tagName }.contains(tags[4].name))
    }

    @Test
    fun test_that_all_questions_are_deleted() {
        val questions = TestUtils.createQuestions(3)
        questionDao.insertAll(questions)
        var allQuestions = questionDao.getAll()

        assertTrue(allQuestions.isNotEmpty())

        questionDao.deleteAll()
        allQuestions = questionDao.getAll()

        assertTrue(allQuestions.isEmpty())
    }
}