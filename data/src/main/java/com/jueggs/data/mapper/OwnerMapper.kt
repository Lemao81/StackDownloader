package com.jueggs.data.mapper

import com.jueggs.data.entity.OwnerEntity
import com.jueggs.domain.model.Owner
import org.mapstruct.*
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