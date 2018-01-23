package com.jueggs.stackdownloader.view

import com.jueggs.utils.base.BaseView

interface SearchCriteriaView : BaseView {
    fun getPageSize(): String = TODO("not implemented")
    fun getSortOrder(): String = TODO("not implemented")
    fun getOrderType(): String = TODO("not implemented")
    fun getMinScore(): String = TODO("not implemented")
    fun getTags(): List<String> = TODO("not implemented")
}