package com.jueggs.data

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jueggs.data.dao.*
import com.jueggs.data.entity.QuestionTagJoinEntity
import org.hamcrest.core.IsEqual.equalTo
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionTagJoinDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var questionDao: QuestionDao
    private lateinit var tagDao: TagDao
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
    fun test_that_question_tag_joins_are_inserted_and_retrieved() {
        val questions = TestUtils.createQuestions(2)
        questionDao.insertAll(questions)
        val tags = TestUtils.createTags(3)
        tagDao.insertAll(tags)

        val joins = listOf(
                QuestionTagJoinEntity(questions[0].id, tags[0].name),
                QuestionTagJoinEntity(questions[0].id, tags[1].name),
                QuestionTagJoinEntity(questions[1].id, tags[0].name),
                QuestionTagJoinEntity(questions[1].id, tags[1].name),
                QuestionTagJoinEntity(questions[1].id, tags[2].name)
        )
        questionTagJoinDao.insertAll(joins)
        val allJoins = questionTagJoinDao.getAll()

        assertThat(joins.size, equalTo(allJoins.size))
    }
}