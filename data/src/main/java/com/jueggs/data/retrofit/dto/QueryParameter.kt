package com.jueggs.data.retrofit.dto

import android.content.Context
import com.jueggs.andutils.extension.*
import com.jueggs.data.*
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.jutils.extension.join
import java.util.*

class QueryParameter(
        var page: Int? = null,
        var pageSize: Int? = null,
        var fromDate: Date? = null,
        var toDate: Date? = null,
        var order: String? = null,
        var sort: String? = null,
        var tags: List<String>? = null,
        var inName: String? = null
) {
    fun asMap(addFilter: Boolean = true): Map<String, String> {
        val map = mutableMapOf<String, String?>()

        map[QUERY_PARAM_PAGE] = page?.toString()
        map[QUERY_PARAM_PAGESIZE] = pageSize?.toString()
        map[QUERY_PARAM_FROMDATE] = fromDate?.unix?.toString()
        map[QUERY_PARAM_TODATE] = toDate?.unix?.toString()
        map[QUERY_PARAM_ORDER] = order
        map[QUERY_PARAM_SORT] = sort
        map[QUERY_PARAM_TAGGED] = tags?.join(";")
        map[QUERY_PARAM_SITE] = SITE
        if (addFilter) map[QUERY_PARAM_FILTER] = FILTER_BODY

        return map.filterValues { it != null }.mapValues { it -> it.value!! }
    }
}

fun SearchCriteria.mapToQueryParameter(context: Context): QueryParameter = QueryParameter().also {
    it.pageSize = 50
    it.sort = getSortString(context, sortType)
    it.order = getOrderString(context, orderType)
    it.tags = tags
    it.fromDate = from
    it.toDate = to
}

fun getSortString(context: Context, index: Int) = context.getStringArray(R.array.sortType)[index]

fun getOrderString(context: Context, index: Int) = context.getStringArray(R.array.orderType)[index]