package com.jueggs.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jueggs.data.dao.OwnerDao
import com.jueggs.data.dao.QuestionDao
import com.jueggs.data.dao.QuestionTagJoinDao
import com.jueggs.data.dao.TagDao
import com.jueggs.data.entity.QuestionTagJoinEntity
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var tagDao: TagDao
    private lateinit var questionDao: QuestionDao
    private lateinit var questionTagJoinDao: QuestionTagJoinDao
    private lateinit var ownerDao: OwnerDao

    @Before
    fun setup() {
        database = AppDatabase.getInMemoryInstance(InstrumentationRegistry.getInstrumentation().context)
        questionDao = database.questionDao()
        tagDao = database.tagDao()
        questionTagJoinDao = database.questionTagJoinDao()
        ownerDao = database.ownerDao()
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
    fun test_that_all_ids_are_retrieved() {
        val questions = TestUtils.createQuestions(3)
        val idsExpected = questions.map { it.id }
        questionDao.insertAll(questions)
        val ids = questionDao.getAllIds()

        assertThat(ids, equalTo(idsExpected))
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

    @Test
    fun test_that_all_questions_with_owner_and_tags_are_retrieved() {
        val tags = TestUtils.createTags(5)
        val questions = TestUtils.createQuestions(2)
        val owner = TestUtils.createOwners(2)
        questions[0].ownerId = owner[0].id ?: 1
        questions[1].ownerId = owner[1].id ?: 2

        tagDao.insertAll(tags)
        questionDao.insertAll(questions)
        ownerDao.insertAll(owner)
        questionTagJoinDao.insertAll(listOf(
                QuestionTagJoinEntity(questions[0].id, tags[0].name),
                QuestionTagJoinEntity(questions[0].id, tags[1].name),
                QuestionTagJoinEntity(questions[1].id, tags[2].name),
                QuestionTagJoinEntity(questions[1].id, tags[3].name),
                QuestionTagJoinEntity(questions[1].id, tags[4].name)
        ))

        val questionsWithOwner = questionDao.getAllIncludingOwnerAndTags()

        assertThat(questionsWithOwner.size, equalTo(2))
        assertThat(questionsWithOwner[0].owner.name, equalTo(owner[0].name))
        assertThat(questionsWithOwner[0].question.id, equalTo(questions[0].id))
        assertThat(questionsWithOwner[1].owner.name, equalTo(owner[1].name))
        assertThat(questionsWithOwner[1].question.id, equalTo(questions[1].id))
        assertThat(questionsWithOwner[0].tags.size, equalTo(2))
        assertThat(questionsWithOwner[1].tags.size, equalTo(3))
        assertTrue(questionsWithOwner[0].tags.map { it.tagName }.contains(tags[0].name))
        assertTrue(questionsWithOwner[0].tags.map { it.tagName }.contains(tags[1].name))
        assertTrue(questionsWithOwner[1].tags.map { it.tagName }.contains(tags[2].name))
        assertTrue(questionsWithOwner[1].tags.map { it.tagName }.contains(tags[3].name))
        assertTrue(questionsWithOwner[1].tags.map { it.tagName }.contains(tags[4].name))
    }
}