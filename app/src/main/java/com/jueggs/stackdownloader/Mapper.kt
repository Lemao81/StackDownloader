package com.jueggs.stackdownloader

import com.jueggs.data.entity.*
import com.jueggs.domain.model.*
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface QuestionMapper {
    fun mapBoToEntity(bo: Question): QuestionEntity
    fun mapEntityToBo(dto: QuestionEntity): Question

    companion object {
        val INSTANCE: QuestionMapper = Mappers.getMapper(QuestionMapper::class.java)
    }
}

val QuestionEntity.bo: Question
    get() = QuestionMapper.INSTANCE.mapEntityToBo(this)

val Question.entity: QuestionEntity
    get() = QuestionMapper.INSTANCE.mapBoToEntity(this)


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface AnswerMapper {
    fun mapBoToEntity(bo: Answer): AnswerEntity
    fun mapEntityToBo(dto: AnswerEntity): Answer

    companion object {
        val INSTANCE: AnswerMapper = Mappers.getMapper(AnswerMapper::class.java)
    }
}

val AnswerEntity.bo: Answer
    get() = AnswerMapper.INSTANCE.mapEntityToBo(this)

val Answer.entity: AnswerEntity
    get() = AnswerMapper.INSTANCE.mapBoToEntity(this)


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface OwnerMapper {
    fun mapBoToEntity(bo: Owner): OwnerEntity
    fun mapEntityToBo(dto: OwnerEntity): Owner

    companion object {
        val INSTANCE: OwnerMapper = Mappers.getMapper(OwnerMapper::class.java)
    }
}

val OwnerEntity.bo: Owner
    get() = OwnerMapper.INSTANCE.mapEntityToBo(this)

val Owner.entity: OwnerEntity
    get() = OwnerMapper.INSTANCE.mapBoToEntity(this)


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface TagMapper {
    fun mapBoToEntity(bo: Tag): TagEntity
    fun mapEntityToBo(dto: TagEntity): Tag

    companion object {
        val INSTANCE: TagMapper = Mappers.getMapper(TagMapper::class.java)
    }
}

val TagEntity.bo: Tag
    get() = TagMapper.INSTANCE.mapEntityToBo(this)

val Tag.entity: TagEntity
    get() = TagMapper.INSTANCE.mapBoToEntity(this)