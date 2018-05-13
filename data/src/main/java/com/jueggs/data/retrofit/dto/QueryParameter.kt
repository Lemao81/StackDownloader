package com.jueggs.data.retrofit.dto

import android.content.Context
import com.jueggs.andutils.extension.getStringArray
import com.jueggs.data.*
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.jutils.extension.join

class QueryParameter(
        var page: String? = null,
        var pageSize: String? = null,
        var from: String? = null,
        var to: String? = null,
        var order: String = "",
        var sort: String = "",
        var tags: List<String>? = null
) {

    fun getKeyValueMap(): Map<String, String> {
        val queryParams = mutableMapOf<String, String>()

        if (!page.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGE] = page!!
        if (!pageSize.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGESIZE] = pageSize!!
        if (!from.isNullOrEmpty()) queryParams[QUERY_PARAM_FROM] = from!!
        if (!to.isNullOrEmpty()) queryParams[QUERY_PARAM_TO] = to!!
        if (tags != null && tags!!.any()) queryParams[QUERY_PARAM_TAGGED] = tags!!.join(";")

        queryParams[QUERY_PARAM_ORDER] = order
        queryParams[QUERY_PARAM_SORT] = sort
        queryParams[QUERY_PARAM_SITE] = SITE
        queryParams[QUERY_PARAM_FILTER] = FILTER_BODY

        return queryParams
    }
}

fun SearchCriteria.mapToQueryParameter(context: Context): QueryParameter = QueryParameter().also {
    it.pageSize = 50.toString()
    it.sort = getSortString(context, sortType)
    it.order = getOrderString(context, orderType)
    it.tags = tags
    it.from = from?.time.toString()
    it.to = to?.time.toString()
}

fun getSortString(context: Context, index: Int) = context.getStringArray(R.array.sortType)[index]

fun getOrderString(context: Context, index: Int) = context.getStringArray(R.array.orderType)[index]