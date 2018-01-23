package com.jueggs.stackdownloader.activity

import android.view.View
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.presenter.SearchPresenter
import com.jueggs.stackdownloader.view.SearchView
import com.jueggs.utils.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity<SearchView>(), SearchView {
    @Inject
    lateinit var presenter: SearchPresenter
    @Inject
    lateinit var app: App

    override fun layout() = R.layout.activity_search
    override fun inject() = app.applicationComponent.inject(this)
    override fun presenter() = presenter
    override fun self(): SearchView = this
    override fun toolbar(): View? = toolbar
    override fun shallToolbarNavigateBack() = false

    override fun showToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideToolbarHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}