package com.jueggs.stackdownloader.util

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.BindingAdapter
import android.os.Build
import android.widget.LinearLayout
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode
import com.jueggs.customview.stackoverflowtag.StackoverflowTag
import com.jueggs.stackdownloader.BuildConfig

fun isLollipopOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isNougatOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

@BindingAdapter("tagViews")
fun LinearLayout.setTagViews(tagNames: List<String>) {
    removeAllViews()
    tagNames.take(if (AppMode.singlePane) 3 else 5).forEach {
        addView(StackoverflowTag(context, it))
    }
}


//TODO lib
fun <TApplication : Application> AndroidViewModel.doWithNetworkConnection(action: () -> Unit): () -> Boolean = getApplication<TApplication>().doWithNetworkConnection(action)

fun Application.doWithNetworkConnection(action: () -> Unit): () -> Boolean {
    val condition = this::isNetworkConnected
    if (condition())
        action()
    return condition
}

infix fun (() -> Boolean).otherwise(otherwiseAction: () -> Unit) {
    val condition = this
    if (!condition())
        otherwiseAction()
}

fun doShowingProgress(showProgress: MutableLiveData<Boolean>, action: () -> Unit) {
    showProgress.fireTrue()
    action()
    showProgress.fireFalse()
}

val AppMode.isDebug: Boolean
    get() = BuildConfig.Debug