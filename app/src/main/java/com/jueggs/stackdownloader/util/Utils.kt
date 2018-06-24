package com.jueggs.stackdownloader.util

import android.arch.lifecycle.*
import android.databinding.BindingAdapter
import android.os.Build
import android.support.annotation.IdRes
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.Navigation
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

val AppMode.isDebug: Boolean
    get() = BuildConfig.Debug


//TODO lib
fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (T) -> Unit) = this.observe(owner, Observer { it?.let(observer) })

fun View.navigateTo(@IdRes resId: Int) = setOnClickListener(Navigation.createNavigateOnClickListener(resId))