package com.jueggs.stackdownloader.accessory

import android.arch.lifecycle.*

class SingleEventLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<T?>) {
        checkObservers()
        super.observe(owner, Observer { onChanged(observer, it) })
    }

    override fun observeForever(observer: Observer<T?>) {
        checkObservers()
        super.observeForever({ onChanged(observer, it) })
    }

    private fun checkObservers() {
        if (hasObservers())
            throw Throwable("Only one observer at a time may subscribe to a SingleEventLiveData")
    }

    private fun onChanged(observer: Observer<T?>, data: T?) {
        observer.onChanged(data)
        if (data != null)
            value = null
    }
}