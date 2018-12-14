package com.jueggs.data

import com.jueggs.data.mapper.bo
import com.jueggs.data.mapper.entity
import com.jueggs.data.retrofit.dto.AnswerDto
import com.jueggs.data.retrofit.dto.QuestionDto
import com.jueggs.jutils.extension.unixTime
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Date

class MapperTest {
    @Test
    fun test_date_mapping_dto_bo() {
        val date = Date()
        val questionDto = QuestionDto(creationDate = date.unixTime, tags = emptyList())
        val answerDto = AnswerDto(creationDate = date.unixTime)

        val questionBo = questionDto.bo
        val answerBo = answerDto.bo

        assertTrue(Math.abs((questionBo.creationDate?.millis ?: 0) - date.time) <= 1000)
        assertTrue(Math.abs((answerBo.creationDate?.millis ?: 0) - date.time) <= 1000)
    }

    @Test
    fun test_date_mapping_dto_entity() {
        val date = Date()
        val questionDto = QuestionDto(creationDate = date.unixTime, tags = emptyList())
        val answerDto = AnswerDto(creationDate = date.unixTime)

        val questionEntity = questionDto.entity
        val answerEntity = answerDto.entity

        assertTrue(Math.abs((questionEntity.creationDate?.time ?: 0) - date.time) <= 1000)
        assertTrue(Math.abs((answerEntity.creationDate?.time ?: 0) - date.time) <= 1000)
    }
}