package com.jueggs.data.mapper

import com.jueggs.andutils.converter.DateTimeUnixLongConverter
import com.jueggs.data.retrofit.dto.AnswerDto
import com.jueggs.domain.model.Answer
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(DateTimeUnixLongConverter::class), (OwnerDtoMapper::class)])
interface AnswerDtoMapper {
    @Mappings(value = [(Mapping(source = "creationDateTime", target = "creationUnixTime"))])
    fun mapToDto(bo: Answer): AnswerDto

    @InheritInverseConfiguration
    fun mapToBo(dto: AnswerDto): Answer

    companion object {
        val INSTANCE: AnswerDtoMapper = Mappers.getMapper(AnswerDtoMapper::class.java)
    }
}

fun Answer.mapToDto() = AnswerDtoMapper.INSTANCE.mapToDto(this)

fun AnswerDto.mapToBo() = AnswerDtoMapper.INSTANCE.mapToBo(this)