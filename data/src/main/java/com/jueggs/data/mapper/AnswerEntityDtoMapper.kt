package com.jueggs.data.mapper

import com.jueggs.andutils.helper.UnixDateConverter
import com.jueggs.data.entity.AnswerEntity
import com.jueggs.data.retrofit.dto.AnswerDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(UnixDateConverter::class)])
interface AnswerEntityDtoMapper {
    @InheritInverseConfiguration
    fun mapToEntity(dto: AnswerDto): AnswerEntity

    @Mappings(value = [(Mapping(source = "ownerId", target = "owner.id"))])
    fun mapToDto(entity: AnswerEntity): AnswerDto

    companion object {
        val INSTANCE: AnswerEntityDtoMapper = Mappers.getMapper(AnswerEntityDtoMapper::class.java)
    }
}

fun AnswerEntity.mapToDto() = AnswerEntityDtoMapper.INSTANCE.mapToDto(this)

fun AnswerDto.mapToEntity() = AnswerEntityDtoMapper.INSTANCE.mapToEntity(this)