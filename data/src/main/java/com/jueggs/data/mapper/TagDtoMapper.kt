package com.jueggs.data.mapper

import com.jueggs.data.retrofit.dto.TagDto
import com.jueggs.domain.model.Tag
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface TagDtoMapper {
    fun mapToDto(bo: Tag): TagDto
    fun mapToBo(dto: TagDto): Tag

    companion object {
        val INSTANCE: TagDtoMapper = Mappers.getMapper(TagDtoMapper::class.java)
    }
}

fun Tag.mapToDto() = TagDtoMapper.INSTANCE.mapToDto(this)

fun TagDto.mapToBo() = TagDtoMapper.INSTANCE.mapToBo(this)