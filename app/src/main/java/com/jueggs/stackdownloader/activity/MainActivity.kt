package com.jueggs.stackdownloader.activity

import android.app.Application
import android.view.View
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.Navigator
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.presenter.interfaces.IMainPresenter
import com.jueggs.stackdownloader.view.MainView
import com.jueggs.stackdownloader.view.MainViewModel
import com.jueggs.utils.base.BaseActivity
import com.jueggs.utils.base.BasePresenter
import com.jueggs.utils.executeDelayed
import javax.inject.Inject

class MainActivity : BaseActivity<MainView, MainViewModel>(), MainView {
    @Inject
    lateinit var presenter: IMainPresenter
    @Inject
    lateinit var navigator: Navigator

    override fun layout() = R.layout.activity_main
    override fun inject() = App.presenterComponent.inject(this)
    override fun presenter() = presenter as BasePresenter<MainView, MainViewModel>
    override fun self() = this
    override fun toolbar(): View? = null

    override fun viewModel() = MainViewModel.EMPTY

    override fun onResume() {
        super.onResume()
        executeDelayed(2000) { navigator.navigateFromMainToSearchActivity(this) }
    }
}