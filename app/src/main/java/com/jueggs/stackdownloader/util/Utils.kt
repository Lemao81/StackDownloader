package com.jueggs.stackdownloader.util

import android.os.Build
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
fun View.navigateOnClick(@IdRes resId: Int) = setOnClickListener(Navigation.createNavigateOnClickListener(resId))