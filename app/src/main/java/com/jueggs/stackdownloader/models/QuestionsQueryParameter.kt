package com.jueggs.stackdownloader.models

import com.jueggs.stackdownloader.utils.*

class QuestionsQueryParameter(private val page: String?, private val pagesize: String?, private val fromdate: String?, private val todate: String?, private val order: String?,
                              private val min: String?, private val max: String?, private val sort: String?, private val tagged: List<String>?) {
    fun build(): Map<String, String> {
        val queryParams = mutableMapOf<String, String>()
        if (!page.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGE] = page!!
        if (!pagesize.isNullOrEmpty()) queryParams[QUERY_PARAM_PAGESIZE] = pagesize!!
        if (!fromdate.isNullOrEmpty()) queryParams[QUERY_PARAM_FROMDATE] = fromdate!!
        if (!todate.isNullOrEmpty()) queryParams[QUERY_PARAM_TODATE] = todate!!
        if (!order.isNullOrEmpty()) queryParams[QUERY_PARAM_ORDER] = order!!
        if (!min.isNullOrEmpty()) queryParams[QUERY_PARAM_MIN] = min!!
        if (!max.isNullOrEmpty()) queryParams[QUERY_PARAM_MAX] = max!!
        if (!sort.isNullOrEmpty()) queryParams[QUERY_PARAM_SORT] = sort!!
        if (tagged != null && tagged.any()) queryParams[QUERY_PARAM_TAGGED] = tagged.join(";")
        queryParams[QUERY_PARAM_SITE] = PARAM_SITE

        return queryParams
    }

    class Builder(private var page: String? = null, private var pagesize: String? = null, private var fromdate: String? = null, private var todate: String? = null, private var order: String? = null,
                  private var min: String? = null, private var max: String? = null, private var sort: String? = null, private var tagged: List<String>? = null) {

        fun pagesize(value: String): Builder {
            pagesize = value
            return this
        }

        fun sort(value: String?): Builder {
            sort = value
            return this
        }

        fun min(value: String): Builder {
            min = value
            return this
        }

        fun tagged(value: List<String>): Builder {
            tagged = value
            return this
        }

        fun build(): QuestionsQueryParameter {
            return QuestionsQueryParameter(page, pagesize, fromdate, todate, order, min, max, sort, tagged)
        }
    }
}