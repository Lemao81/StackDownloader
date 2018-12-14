package com.jueggs.data.mapper

import com.jueggs.andutils.helper.UnixDateConverter
import com.jueggs.data.entity.AnswerEntity
import com.jueggs.data.retrofit.dto.AnswerDto
import com.jueggs.domain.model.Answer
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface AnswerEntityMapper {
    @Mappings(value = [(Mapping(source = "owner.id", target = "ownerId"))])
    fun mapBoToEntity(bo: Answer): AnswerEntity

    @InheritInverseConfiguration
    fun mapEntityToBo(dto: AnswerEntity): Answer

    companion object {
        val INSTANCE: AnswerEntityMapper = Mappers.getMapper(AnswerEntityMapper::class.java)
    }
}

val AnswerEntity.bo: Answer
    get() = AnswerEntityMapper.INSTANCE.mapEntityToBo(this)

val Answer.entity: AnswerEntity
    get() = AnswerEntityMapper.INSTANCE.mapBoToEntity(this)

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(UnixDateConverter::class), (OwnerDtoMapper::class)])
interface AnswerDtoMapper {
    fun mapBoToDto(bo: Answer): AnswerDto
    fun mapDtoToBo(dto: AnswerDto): Answer

    companion object {
        val INSTANCE: AnswerDtoMapper = Mappers.getMapper(AnswerDtoMapper::class.java)
    }
}

val AnswerDto.bo: Answer
    get() = AnswerDtoMapper.INSTANCE.mapDtoToBo(this)

val Answer.dto: AnswerDto
    get() = AnswerDtoMapper.INSTANCE.mapBoToDto(this)

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = [(UnixDateConverter::class)])
interface AnswerEntityDtoMapper {
    @InheritInverseConfiguration
    fun mapDtoToEntity(dto: AnswerDto): AnswerEntity

    @Mappings(value = [(Mapping(source = "ownerId", target = "owner.id"))])
    fun mapEntityToDto(entity: AnswerEntity): AnswerDto

    companion object {
        val INSTANCE: AnswerEntityDtoMapper = Mappers.getMapper(AnswerEntityDtoMapper::class.java)
    }
}

val AnswerEntity.dto: AnswerDto
    get() = AnswerEntityDtoMapper.INSTANCE.mapEntityToDto(this)

val AnswerDto.entity: AnswerEntity
    get() = AnswerEntityDtoMapper.INSTANCE.mapDtoToEntity(this)