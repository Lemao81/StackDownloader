package com.jueggs.stackdownloader.view

import android.arch.lifecycle.Lifecycle
import com.jueggs.utils.base.BaseView
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

interface MainView : BaseView {

}

@PaperParcel
class MainViewModel : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelMainViewModel.CREATOR
        val EMPTY = MainViewModel()
    }
}

class MainViewStub : MainView {
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented")
    }
}