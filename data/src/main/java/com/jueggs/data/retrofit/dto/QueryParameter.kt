package com.jueggs.data.retrofit.dto

import android.content.Context
import com.jueggs.andutils.extension.getStringArray
import com.jueggs.data.*
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.jutils.extension.join

class QueryParameter(var page: String? = null, var limitTo: String? = null, var fromdate: String? = null, var todate: String? = null, var order: String = "",
                     var min: String? = null, var max: String? = null, var sort: String = "", var tags: List<String>? = null) {

    fun getKeyValueMap(): Map<String, String> {
        val queryParams = mutableMapOf<String, String>()
        if (!page.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGE] = page!!
        if (!limitTo.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGESIZE] = limitTo!!
        if (!fromdate.isNullOrEmpty()) queryParams[QUERY_PARAM_FROMDATE] = fromdate!!
        if (!todate.isNullOrEmpty()) queryParams[QUERY_PARAM_TODATE] = todate!!
        if (!min.isNullOrEmpty()) queryParams[QUERY_PARAM_MIN] = min!!
        if (!max.isNullOrEmpty()) queryParams[QUERY_PARAM_MAX] = max!!
        if (tags != null && tags!!.any()) queryParams[QUERY_PARAM_TAGGED] = tags!!.join(";")
        queryParams[QUERY_PARAM_ORDER] = order
        queryParams[QUERY_PARAM_SORT] = sort
        queryParams[QUERY_PARAM_SITE] = SITE
        queryParams[QUERY_PARAM_FILTER] = FILTER_BODY

        return queryParams
    }
}

fun SearchCriteria.mapToQueryParameter(context: Context): QueryParameter = QueryParameter().also {
    it.limitTo = limitTo
    it.sort = getSortString(context, sortType)
    it.order = getOrderString(context, orderType)
    it.min = score
    it.tags = tags
}

fun getSortString(context: Context, index: Int) = context.getStringArray(R.array.sortType)[index]

fun getOrderString(context: Context, index: Int) = context.getStringArray(R.array.orderType)[index]