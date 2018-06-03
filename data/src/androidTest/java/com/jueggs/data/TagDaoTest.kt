package com.jueggs.data

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jueggs.data.dao.TagDao
import org.hamcrest.core.IsEqual.equalTo
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TagDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var tagDao: TagDao

    @Before
    fun setup() {
        database = AppDatabase.getInMemoryInstance(InstrumentationRegistry.getContext())
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