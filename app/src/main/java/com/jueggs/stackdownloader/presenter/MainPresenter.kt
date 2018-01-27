package com.jueggs.stackdownloader.presenter

import com.jueggs.stackdownloader.presenter.interfaces.IMainPresenter
import com.jueggs.stackdownloader.view.MainView
import com.jueggs.stackdownloader.view.MainViewModel
import com.jueggs.stackdownloader.view.MainViewStub
import com.jueggs.utils.base.BasePresenter

class MainPresenter : BasePresenter<MainView, MainViewModel>(), IMainPresenter {
    override fun viewStub(): MainView = MainViewStub()
}