package com.jueggs.data.mapper

import com.jueggs.data.entity.OwnerEntity
import com.jueggs.domain.model.Owner
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface OwnerEntityMapper {
    fun mapToEntity(bo: Owner): OwnerEntity
    fun mapToBo(dto: OwnerEntity): Owner

    companion object {
        val INSTANCE: OwnerEntityMapper = Mappers.getMapper(OwnerEntityMapper::class.java)
    }
}

fun OwnerEntity.mapToBo() = OwnerEntityMapper.INSTANCE.mapToBo(this)

fun Owner.mapToEntity() = OwnerEntityMapper.INSTANCE.mapToEntity(this)