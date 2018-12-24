package com.jueggs.data.mapper

import com.jueggs.andutils.converter.DateTimeUnixLongConverter
import com.jueggs.data.retrofit.dto.QuestionDto
import com.jueggs.domain.model.Question
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(DateTimeUnixLongConverter::class), (OwnerDtoMapper::class)])
interface QuestionDtoMapper {
    @Mappings(value = [(Mapping(source = "creationDateTime", target = "creationUnixTime"))])
    fun mapToDto(bo: Question): QuestionDto

    @InheritInverseConfiguration
    fun mapToBo(dto: QuestionDto): Question

    companion object {
        val INSTANCE: QuestionDtoMapper = Mappers.getMapper(QuestionDtoMapper::class.java)
    }
}

fun Question.mapToDto() = QuestionDtoMapper.INSTANCE.mapToDto(this)

fun QuestionDto.mapToBo() = QuestionDtoMapper.INSTANCE.mapToBo(this)