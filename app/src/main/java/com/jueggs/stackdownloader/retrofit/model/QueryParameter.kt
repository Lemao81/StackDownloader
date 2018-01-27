package com.jueggs.stackdownloader.retrofit.model

import com.jueggs.stackdownloader.util.*
import com.jueggs.utils.extension.join

class QueryParameter(private val page: String?, private val pagesize: String?, private val fromdate: String?, private val todate: String?, private val order: String,
                     private val min: String?, private val max: String?, private val sort: String, private val tags: List<String>?) {
    fun build(): Map<String, String> {
        val queryParams = mutableMapOf<String, String>()
        if (!page.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGE] = page!!
        if (!pagesize.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGESIZE] = pagesize!!
        if (!fromdate.isNullOrEmpty()) queryParams[QUERY_PARAM_FROMDATE] = fromdate!!
        if (!todate.isNullOrEmpty()) queryParams[QUERY_PARAM_TODATE] = todate!!
        if (!min.isNullOrEmpty()) queryParams[QUERY_PARAM_MIN] = min!!
        if (!max.isNullOrEmpty()) queryParams[QUERY_PARAM_MAX] = max!!
        if (tags != null && tags.any()) queryParams[QUERY_PARAM_TAGGED] = tags.join(";")
        queryParams[QUERY_PARAM_ORDER] = order
        queryParams[QUERY_PARAM_SORT] = sort
        queryParams[QUERY_PARAM_SITE] = SITE
        queryParams[QUERY_PARAM_FILTER] = FILTER_BODY

        return queryParams
    }

    class Builder(private var page: String? = null, private var pagesize: String? = null, private var fromdate: String? = null, private var todate: String? = null,
                  private var order: String = ORDER_DESC, private var min: String? = null, private var max: String? = null, private var sort: String = SORT_CREATION,
                  private var tags: List<String>? = null) {

        fun pagesize(value: String): Builder {
            pagesize = value
            return this
        }

        fun sort(value: String): Builder {
            sort = value
            return this
        }

        fun order(value: String): Builder {
            order = value
            return this
        }

        fun min(value: String): Builder {
            min = value
            return this
        }

        fun tags(value: List<String>): Builder {
            tags = value
            return this
        }

        fun build(): QueryParameter {
            return QueryParameter(page, pagesize, fromdate, todate, order, min, max, sort, tags)
        }
    }
}