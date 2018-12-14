package com.jueggs.data.mapper

import com.jueggs.andutils.helper.UnixDateConverter
import com.jueggs.data.retrofit.dto.AnswerDto
import com.jueggs.domain.model.Answer
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(UnixDateConverter::class), (OwnerDtoMapper::class)])
interface AnswerDtoMapper {
    fun mapToDto(bo: Answer): AnswerDto
    fun mapToBo(dto: AnswerDto): Answer

    companion object {
        val INSTANCE: AnswerDtoMapper = Mappers.getMapper(AnswerDtoMapper::class.java)
    }
}

fun AnswerDto.mapToBo() = AnswerDtoMapper.INSTANCE.mapToBo(this)

fun Answer.mapToDto() = AnswerDtoMapper.INSTANCE.mapToDto(this)