package com.jueggs.stackdownloader.activity

import android.view.View
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.presenter.interfaces.IMainPresenter
import com.jueggs.stackdownloader.view.*
import com.jueggs.utils.base.*
import com.jueggs.utils.executeDelayed
import javax.inject.Inject

class MainActivity : BaseActivity<MainView, MainViewModel>(), MainView {
    @Inject
    lateinit var presenter: IMainPresenter
    @Inject
    lateinit var navigator: Navigator

    override fun layout() = R.layout.activity_main
    override fun inject() = App.applicationComponent.inject(this)
    override fun presenter() = presenter as BasePresenter<MainView, MainViewModel>
    override fun self() = this
    override fun toolbar(): View? = null

    override fun viewModel() = MainViewModel.EMPTY

    override fun onResume() {
        super.onResume()
        executeDelayed(2000) { navigator.navigateFromMainToSearchActivity(this) }
    }
}