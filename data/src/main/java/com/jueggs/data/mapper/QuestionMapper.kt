package com.jueggs.data.mapper

import com.jueggs.data.entity.QuestionEntity
import com.jueggs.data.retrofit.dto.QuestionDto
import com.jueggs.domain.model.Question
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface QuestionEntityMapper {
    fun mapBoToEntity(bo: Question): QuestionEntity
    fun mapEntityToBo(dto: QuestionEntity): Question

    companion object {
        val INSTANCE: QuestionEntityMapper = Mappers.getMapper(QuestionEntityMapper::class.java)
    }
}

val QuestionEntity.bo: Question
    get() = QuestionEntityMapper.INSTANCE.mapEntityToBo(this)

val Question.entity: QuestionEntity
    get() = QuestionEntityMapper.INSTANCE.mapBoToEntity(this)


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface QuestionDtoMapper {
    fun mapBoToDto(bo: Question): QuestionDto
    fun mapDtoToBo(dto: QuestionDto): Question

    companion object {
        val INSTANCE: QuestionDtoMapper = Mappers.getMapper(QuestionDtoMapper::class.java)
    }
}

val QuestionDto.bo: Question
    get() = QuestionDtoMapper.INSTANCE.mapDtoToBo(this)

val Question.dto: QuestionDto
    get() = QuestionDtoMapper.INSTANCE.mapBoToDto(this)


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface QuestionEntityDtoMapper {
    fun mapDtoToEntity(dto: QuestionDto): QuestionEntity
    fun mapEntityToDto(entity: QuestionEntity): QuestionDto

    companion object {
        val INSTANCE: QuestionEntityDtoMapper = Mappers.getMapper(QuestionEntityDtoMapper::class.java)
    }
}

val QuestionEntity.dto: QuestionDto
    get() = QuestionEntityDtoMapper.INSTANCE.mapEntityToDto(this)

val QuestionDto.entity: QuestionEntity
    get() = QuestionEntityDtoMapper.INSTANCE.mapDtoToEntity(this)