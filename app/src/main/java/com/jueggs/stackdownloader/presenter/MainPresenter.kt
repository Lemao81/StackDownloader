package com.jueggs.stackdownloader.presenter

import com.jueggs.stackdownloader.view.MainView
import com.jueggs.utils.base.BaseActivityPresenter
import com.jueggs.utils.base.LifecycleOwnerStub

class MainPresenter : BaseActivityPresenter<MainView>() {
    override fun viewStub(): MainView = object : MainView, LifecycleOwnerStub {}
}