package com.jueggs.stackdownloader

import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.model.dto.SearchCriteriaDto
import org.mapstruct.*
import org.mapstruct.factory.Mappers

//region searchcriteria
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface SearchCriteriaMapper {
    fun mapBoToDto(bo: SearchCriteria): SearchCriteriaDto
    fun mapDtoToBo(dto: SearchCriteriaDto): SearchCriteria

    companion object {
        val INSTANCE: SearchCriteriaMapper = Mappers.getMapper(SearchCriteriaMapper::class.java)
    }
}

val SearchCriteriaDto.bo: SearchCriteria
    get() = SearchCriteriaMapper.INSTANCE.mapDtoToBo(this)

val SearchCriteria.dto: SearchCriteriaDto
    get() = SearchCriteriaMapper.INSTANCE.mapBoToDto(this)
//endregion