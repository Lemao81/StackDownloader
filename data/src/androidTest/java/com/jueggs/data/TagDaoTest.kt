package com.jueggs.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jueggs.data.dao.TagDao
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TagDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var tagDao: TagDao

    @Before
    fun setup() {
        database = AppDatabase.getInMemoryInstance(InstrumentationRegistry.getInstrumentation().context)
        tagDao = database.tagDao()
    }

    @After
    fun tearDown() = database.close()

    @Test
    fun test_that_tags_are_inserted_and_retrieved() {
        val tags = TestUtils.createTags(3)
        tagDao.insertAll(tags)
        val allTags = tagDao.getAll()

        assertThat(allTags.size, equalTo(tags.size))
    }

    @Test
    fun test_that_all_names_are_retrieved() {
        val tags = TestUtils.createTags(3)
        tagDao.insertAll(tags)
        val names = tagDao.getAllNames()

        assertThat(names.size, equalTo(3))
        assertTrue(names.contains(tags[0].name))
        assertTrue(names.contains(tags[1].name))
        assertTrue(names.contains(tags[2].name))
    }
}