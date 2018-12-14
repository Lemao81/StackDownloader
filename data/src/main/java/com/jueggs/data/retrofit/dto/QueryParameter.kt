package com.jueggs.data.retrofit.dto

import android.content.Context
import com.jueggs.andutils.extension.getStringArray
import com.jueggs.data.FILTER_BODY
import com.jueggs.data.QUERY_PARAM_FILTER
import com.jueggs.data.QUERY_PARAM_FROMDATE
import com.jueggs.data.QUERY_PARAM_ORDER
import com.jueggs.data.QUERY_PARAM_PAGE
import com.jueggs.data.QUERY_PARAM_PAGESIZE
import com.jueggs.data.QUERY_PARAM_SITE
import com.jueggs.data.QUERY_PARAM_SORT
import com.jueggs.data.QUERY_PARAM_TAGGED
import com.jueggs.data.QUERY_PARAM_TODATE
import com.jueggs.data.R
import com.jueggs.data.SITE
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.jutils.extension.join
import com.jueggs.jutils.extension.unixTime
import org.joda.time.LocalDate

class QueryParameter(
    var page: Int? = null,
    var pageSize: Int? = null,
    var fromDate: LocalDate? = null,
    var toDate: LocalDate? = null,
    var order: String? = null,
    var sort: String? = null,
    var tags: List<String>? = null,
    var inName: String? = null
) {
    fun asMap(addFilter: Boolean = true): Map<String, String> {
        val map = mutableMapOf<String, String?>()

        map[QUERY_PARAM_PAGE] = page?.toString()
        map[QUERY_PARAM_PAGESIZE] = pageSize?.toString()
        map[QUERY_PARAM_FROMDATE] = fromDate?.unixTime?.toString()
        map[QUERY_PARAM_TODATE] = toDate?.unixTime?.toString()
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
    it.sort = getSortString(context, sortType ?: 0)
    it.order = getOrderString(context, orderType ?: 0)
    it.tags = tags
    it.fromDate = from
    it.toDate = to
}

fun getSortString(context: Context, index: Int) = context.getStringArray(R.array.sortType)[index]

fun getOrderString(context: Context, index: Int) = context.getStringArray(R.array.orderType)[index]