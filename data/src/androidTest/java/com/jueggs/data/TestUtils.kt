package com.jueggs.data

import com.jueggs.data.entity.*
import com.jueggs.jutils.*
import com.jueggs.jutils.helper.*
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat

class TestUtils {
    companion object {
        private val tagNames = listOf("javascript", "java", "c#", "html", "css", "android", "kotlin", "angularjs", "knockout", "spring", "jquery")
        private val randomString = RandomString()

        fun createTags(count: Int): List<TagEntity> {
            val distinctRandom = DistinctRandom(tagNames)
            val result = mutableListOf<TagEntity>()

            (1..count).forEach { result.add(TagEntity(distinctRandom.next(), randomInt())) }
            assertThat(result.size, equalTo(count))

            return result
        }

        fun createQuestions(count: Int): List<QuestionEntity> {
            val result = mutableListOf<QuestionEntity>()

            (1..count).forEach {
                result.add(QuestionEntity(it.toLong(), randomLong(), false, randomInt(), randomInt(), randomInt(), randomDate(),
                        randomString.nextString(), randomString.nextString(), randomString.nextString()))
            }
            assertThat(result.size, equalTo(count))

            return result
        }

        fun createOwners(count: Int): List<OwnerEntity> {
            val result = mutableListOf<OwnerEntity>()

            (1..count).forEach { result.add(OwnerEntity(it.toLong(), randomInt(), randomString.nextString(), randomString.nextString())) }
            assertThat(result.size, equalTo(count))

            return result
        }
    }
}