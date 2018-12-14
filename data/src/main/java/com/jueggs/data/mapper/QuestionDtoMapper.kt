package com.jueggs.data.mapper

import com.jueggs.andutils.helper.UnixDateConverter
import com.jueggs.data.retrofit.dto.QuestionDto
import com.jueggs.domain.model.Question
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(UnixDateConverter::class), (OwnerDtoMapper::class)])
interface QuestionDtoMapper {
    fun mapToDto(bo: Question): QuestionDto
    fun mapToBo(dto: QuestionDto): Question

    companion object {
        val INSTANCE: QuestionDtoMapper = Mappers.getMapper(QuestionDtoMapper::class.java)
    }
}

fun QuestionDto.mapToBo() = QuestionDtoMapper.INSTANCE.mapToBo(this)

fun Question.mapToDto() = QuestionDtoMapper.INSTANCE.mapToDto(this)