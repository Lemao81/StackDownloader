package com.jueggs.stackdownloader.bo

import com.jueggs.stackdownloader.data.entity.*
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface QuestionMapper {
    @Mappings(
            Mapping(source = "questionId", target = "id"),
            Mapping(source = "owner.userId", target = "ownerId"),
            Mapping(target = "owner", ignore = true))
    fun mapBoToEntity(bo: Question): QuestionEntity

    @InheritInverseConfiguration
    fun mapEntityToBo(entity: QuestionEntity): Question
}

@Mapper
interface AnswerMapper {
    @Mappings(
            Mapping(source = "answerId", target = "id"),
            Mapping(source = "owner.userId", target = "ownerId"),
            Mapping(target = "owner", ignore = true),
            Mapping(target = "question", ignore = true))
    fun mapBoToEntity(bo: Answer): AnswerEntity

    @InheritInverseConfiguration
    fun mapEntityToBo(entity: AnswerEntity): Answer
}

@Mapper
interface OwnerMapper {
    @Mapping(source = "userId", target = "id")
    fun mapBoToEntity(bo: Owner): OwnerEntity

    @InheritInverseConfiguration
    fun mapEntityToBo(entity: OwnerEntity): Owner
}