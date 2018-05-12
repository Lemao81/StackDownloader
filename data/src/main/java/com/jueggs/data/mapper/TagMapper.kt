package com.jueggs.data.mapper

import com.jueggs.data.entity.TagEntity
import com.jueggs.domain.model.Tag
import org.mapstruct.*
import org.mapstruct.factory.Mappers

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