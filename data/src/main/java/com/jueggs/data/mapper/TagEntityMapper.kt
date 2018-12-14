package com.jueggs.data.mapper

import com.jueggs.data.entity.TagEntity
import com.jueggs.domain.model.Tag
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface TagEntityMapper {
    fun mapToEntity(bo: Tag): TagEntity
    fun mapToBo(dto: TagEntity): Tag

    companion object {
        val INSTANCE: TagEntityMapper = Mappers.getMapper(TagEntityMapper::class.java)
    }
}

fun TagEntity.mapToBo() = TagEntityMapper.INSTANCE.mapToBo(this)

fun Tag.mapToEntity() = TagEntityMapper.INSTANCE.mapToEntity(this)