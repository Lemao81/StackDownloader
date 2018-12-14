package com.jueggs.data.mapper

import com.jueggs.data.entity.AnswerEntity
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
    fun mapToEntity(bo: Answer): AnswerEntity

    @InheritInverseConfiguration
    fun mapToBo(dto: AnswerEntity): Answer

    companion object {
        val INSTANCE: AnswerEntityMapper = Mappers.getMapper(AnswerEntityMapper::class.java)
    }
}

fun AnswerEntity.mapToBo() = AnswerEntityMapper.INSTANCE.mapToBo(this)

fun Answer.mapToEntity() = AnswerEntityMapper.INSTANCE.mapToEntity(this)