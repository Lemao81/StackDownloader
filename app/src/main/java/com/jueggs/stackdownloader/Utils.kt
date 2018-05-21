package com.jueggs.stackdownloader

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.os.Build

fun isLollipopOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isNougatOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

//TODO lib

fun <T> ObservableField<T>.getOr(default: T): T = get() ?: default

fun MutableLiveData<Unit>.fire() {
    value = Unit
}