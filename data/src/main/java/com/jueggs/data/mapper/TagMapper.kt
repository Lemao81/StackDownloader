package com.jueggs.data.mapper

import com.jueggs.data.entity.TagEntity
import com.jueggs.data.retrofit.dto.TagDto
import com.jueggs.domain.model.Tag
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
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

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface TagDtoMapper {
    fun mapBoToDto(bo: Tag): TagDto
    fun mapDtoToBo(dto: TagDto): Tag

    companion object {
        val INSTANCE: TagDtoMapper = Mappers.getMapper(TagDtoMapper::class.java)
    }
}

val TagDto.bo: Tag
    get() = TagDtoMapper.INSTANCE.mapDtoToBo(this)

val Tag.dto: TagDto
    get() = TagDtoMapper.INSTANCE.mapBoToDto(this)