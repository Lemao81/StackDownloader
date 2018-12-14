package com.jueggs.data.mapper

import com.jueggs.data.entity.OwnerEntity
import com.jueggs.data.retrofit.dto.OwnerDto
import com.jueggs.domain.model.Owner
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

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
interface OwnerDtoMapper {
    fun mapBoToDto(bo: Owner): OwnerDto
    fun mapDtoToBo(dto: OwnerDto): Owner

    companion object {
        val INSTANCE: OwnerDtoMapper = Mappers.getMapper(OwnerDtoMapper::class.java)
    }
}

val OwnerDto.bo: Owner
    get() = OwnerDtoMapper.INSTANCE.mapDtoToBo(this)

val Owner.dto: OwnerDto
    get() = OwnerDtoMapper.INSTANCE.mapBoToDto(this)