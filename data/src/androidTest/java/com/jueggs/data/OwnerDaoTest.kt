package com.jueggs.data

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jueggs.data.dao.OwnerDao
import org.hamcrest.core.IsEqual.equalTo
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OwnerDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var ownerDao: OwnerDao

    @Before
    fun setup() {
        database = AppDatabase.getInMemoryInstance(InstrumentationRegistry.getContext())
        ownerDao = database.ownerDao()
    }

    @After
    fun tearDown() = database.close()

    @Test
    fun test_that_owners_are_inserted_and_retrieved() {
        val owner = TestUtils.createOwners(3)
        ownerDao.insertAll(owner)
        val allOwner = ownerDao.getAll()

        assertThat(allOwner.size, equalTo(owner.size))
    }
}