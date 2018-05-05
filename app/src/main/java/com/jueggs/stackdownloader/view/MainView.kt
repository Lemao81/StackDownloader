package com.jueggs.stackdownloader.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.os.Parcelable
import com.jueggs.utils.base.BaseView
import kotlinx.android.parcel.Parcelize

interface MainView : BaseView {

}

@SuppressLint("ParcelCreator")
@Parcelize
class MainViewModel : Parcelable {

    companion object {
        val EMPTY = MainViewModel()
    }
}

class MainViewStub : MainView {
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented")
    }
}