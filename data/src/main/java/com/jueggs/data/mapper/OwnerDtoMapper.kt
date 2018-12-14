package com.jueggs.data.mapper

import com.jueggs.data.retrofit.dto.OwnerDto
import com.jueggs.domain.model.Owner
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface OwnerDtoMapper {
    fun mapToDto(bo: Owner): OwnerDto
    fun mapToBo(dto: OwnerDto): Owner

    companion object {
        val INSTANCE: OwnerDtoMapper = Mappers.getMapper(OwnerDtoMapper::class.java)
    }
}

fun OwnerDto.mapToBo() = OwnerDtoMapper.INSTANCE.mapToBo(this)

fun Owner.mapToDto() = OwnerDtoMapper.INSTANCE.mapToDto(this)