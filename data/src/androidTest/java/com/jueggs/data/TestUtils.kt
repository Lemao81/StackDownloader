package com.jueggs.data

import com.jueggs.data.entity.AnswerEntity
import com.jueggs.data.entity.OwnerEntity
import com.jueggs.data.entity.QuestionEntity
import com.jueggs.data.entity.TagEntity
import com.jueggs.jutils.Random
import com.jueggs.jutils.helper.DistinctRandom
import com.jueggs.jutils.helper.RandomString
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat

class TestUtils {
    companion object {
        private val tagNames = listOf("javascript", "java", "c#", "html", "css", "android", "kotlin", "angularjs", "knockout", "spring", "jquery")
        private val randomString = RandomString()

        fun createTags(count: Int): List<TagEntity> {
            val distinctRandom = DistinctRandom(tagNames)
            val result = mutableListOf<TagEntity>()

            (1..count).forEach { result.add(TagEntity(distinctRandom(), Random.int())) }
            assertThat(result.size, equalTo(count))

            return result
        }

        fun createQuestions(count: Int): List<QuestionEntity> {
            val result = mutableListOf<QuestionEntity>()

            (1..count).forEach {
                result.add(QuestionEntity(it.toLong(), Random.long, false, Random.int(), Random.int(), Random.int(), Random.date(),
                        randomString(), randomString(), randomString()))
            }
            assertThat(result.size, equalTo(count))

            return result
        }

        fun createOwners(count: Int): List<OwnerEntity> {
            val result = mutableListOf<OwnerEntity>()

            (1..count).forEach { result.add(OwnerEntity(it.toLong(), Random.int(), randomString(), randomString())) }
            assertThat(result.size, equalTo(count))

            return result
        }

        fun createAnswers(count: Int): List<AnswerEntity> {
            val result = mutableListOf<AnswerEntity>()

            (1..count).forEach {
                result.add(AnswerEntity(it.toLong(), Random.long, Random.long, false, Random.int(), Random.date(), randomString(), randomString(),
                        randomString()))
            }
            assertThat(result.size, equalTo(count))

            return result
        }
    }
}