package com.jueggs.data.mapper

import com.jueggs.andutils.converter.UnixLongStringDateTimeConverter
import com.jueggs.data.entity.QuestionEntity
import com.jueggs.data.retrofit.dto.QuestionDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(UnixLongStringDateTimeConverter::class)])
interface QuestionEntityDtoMapper {
    @Mappings(value = [
        (Mapping(source = "ownerId", target = "owner.id")),
        (Mapping(target = "tags", ignore = true)),
        (Mapping(source = "creationDateTime", target = "creationUnixTime"))
    ])
    fun mapToDto(entity: QuestionEntity): QuestionDto

    @InheritInverseConfiguration
    fun mapToEntity(dto: QuestionDto): QuestionEntity

    companion object {
        val INSTANCE: QuestionEntityDtoMapper = Mappers.getMapper(QuestionEntityDtoMapper::class.java)
    }
}

fun QuestionEntity.mapToDto() = QuestionEntityDtoMapper.INSTANCE.mapToDto(this)

fun QuestionDto.mapToEntity() = QuestionEntityDtoMapper.INSTANCE.mapToEntity(this)