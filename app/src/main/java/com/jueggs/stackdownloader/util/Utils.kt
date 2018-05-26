package com.jueggs.stackdownloader.util

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.os.Build
import android.widget.LinearLayout
import com.jueggs.andutils.util.AppMode
import com.jueggs.customview.stackoverflowtag.*

fun isLollipopOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isNougatOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

@BindingAdapter("tagViews")
fun LinearLayout.setTagViews(tagNames: List<String>) {
    removeAllViews()
    tagNames.take(if (AppMode.singlePane) 3 else 5).forEach {
        addView(StackoverflowTag(context, it, Size.SMALL))
    }
}

//TODO lib
fun MutableLiveData<Unit>.postFire() = postValue(Unit)