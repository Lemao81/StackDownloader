package com.jueggs.data.mapper

import com.jueggs.data.entity.QuestionEntity
import com.jueggs.domain.model.Question
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface QuestionEntityMapper {
    @InheritInverseConfiguration
    fun mapToEntity(bo: Question): QuestionEntity

    @Mappings(value = [(Mapping(source = "ownerId", target = "owner.id")), (Mapping(target = "tags", ignore = true))])
    fun mapToBo(dto: QuestionEntity): Question

    companion object {
        val INSTANCE: QuestionEntityMapper = Mappers.getMapper(QuestionEntityMapper::class.java)
    }
}

fun QuestionEntity.mapToBo() = QuestionEntityMapper.INSTANCE.mapToBo(this)

fun Question.mapToEntity() = QuestionEntityMapper.INSTANCE.mapToEntity(this)