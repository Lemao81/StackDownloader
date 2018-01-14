package com.jueggs.stackdownloader.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.AdapterView
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.dagger.ApplicationComponent

fun Activity.dagger(): ApplicationComponent {
    return (application as App).applicationComponent
}

fun Fragment.dagger(): ApplicationComponent {
    return (activity!!.application as App).applicationComponent
}

//for util library
fun AdapterView<*>.asString(): String? = if (selectedItem != null) selectedItem.toString() else null

fun List<String>.join(separator: String): String = joinToString(separator) { it }