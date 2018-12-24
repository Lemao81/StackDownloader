package com.jueggs.data

import com.jueggs.data.mapper.mapToBo
import com.jueggs.data.mapper.mapToEntity
import com.jueggs.data.retrofit.dto.AnswerDto
import com.jueggs.data.retrofit.dto.QuestionDto
import com.jueggs.jutils.extension.unixTime
import org.joda.time.DateTime
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs

class MapperTest {
    @Test
    fun test_date_mapping_dto_bo() {
        val date = DateTime()
        val questionDto = QuestionDto(creationUnixTime = date.unixTime, tags = emptyList())
        val answerDto = AnswerDto(creationUnixTime = date.unixTime)

        val questionBo = questionDto.mapToBo()
        val answerBo = answerDto.mapToBo()

        assertTrue(abs((questionBo.creationDateTime?.millis ?: 0) - date.millis) <= 1000)
        assertTrue(abs((answerBo.creationDateTime?.millis ?: 0) - date.millis) <= 1000)
    }

    @Test
    fun test_date_mapping_dto_entity() {
        val date = DateTime()
        val questionDto = QuestionDto(creationUnixTime = date.unixTime, tags = emptyList())
        val answerDto = AnswerDto(creationUnixTime = date.unixTime)

        val questionEntity = questionDto.mapToEntity()
        val answerEntity = answerDto.mapToEntity()

        assertTrue(abs(DateTime.parse(questionEntity.creationDateTime).millis - date.millis) <= 1000)
        assertTrue(abs(DateTime.parse(answerEntity.creationDateTime).millis - date.millis) <= 1000)
    }
}