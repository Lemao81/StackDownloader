package com.jueggs.stackdownloader.presenter

import com.jueggs.stackdownloader.view.SearchView
import com.jueggs.utils.base.BaseActivityPresenter
import com.jueggs.utils.base.LifecycleOwnerStub

class SearchPresenter : BaseActivityPresenter<SearchView>() {
    override fun viewStub(): SearchView = object : SearchView, LifecycleOwnerStub {}
}
