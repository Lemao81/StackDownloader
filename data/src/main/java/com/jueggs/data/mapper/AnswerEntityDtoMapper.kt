package com.jueggs.data.mapper

import com.jueggs.andutils.converter.UnixLongStringDateTimeConverter
import com.jueggs.data.entity.AnswerEntity
import com.jueggs.data.retrofit.dto.AnswerDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(UnixLongStringDateTimeConverter::class)])
interface AnswerEntityDtoMapper {
    @Mappings(value = [
        (Mapping(source = "ownerId", target = "owner.id")),
        (Mapping(source = "creationDateTime", target = "creationUnixTime"))
    ])
    fun mapToDto(entity: AnswerEntity): AnswerDto

    @InheritInverseConfiguration
    fun mapToEntity(dto: AnswerDto): AnswerEntity

    companion object {
        val INSTANCE: AnswerEntityDtoMapper = Mappers.getMapper(AnswerEntityDtoMapper::class.java)
    }
}

fun AnswerEntity.mapToDto() = AnswerEntityDtoMapper.INSTANCE.mapToDto(this)

fun AnswerDto.mapToEntity() = AnswerEntityDtoMapper.INSTANCE.mapToEntity(this)