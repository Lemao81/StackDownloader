package com.jueggs.stackdownloader.activity

import android.view.View
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.Navigator
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.presenter.MainPresenter
import com.jueggs.stackdownloader.view.MainView
import com.jueggs.utils.base.BaseActivity
import com.jueggs.utils.executeDelayed
import javax.inject.Inject

class MainActivity : BaseActivity<MainView>(), MainView {
    @Inject
    lateinit var presenter: MainPresenter
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var app: App

    override fun layout() = R.layout.activity_main
    override fun inject() = app.applicationComponent.inject(this)
    override fun presenter() = presenter
    override fun self() = this
    override fun toolbar(): View? = null

    override fun onResume() {
        super.onResume()
        executeDelayed(2000) { navigator.navigateFromMainToSearchActivity(this) }
    }
}