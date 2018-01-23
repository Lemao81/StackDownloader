package com.jueggs.stackdownloader.util

import android.app.Activity
import android.support.v4.app.Fragment
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.dagger.ApplicationComponent

fun Activity.dagger(): ApplicationComponent {
    return (application as App).applicationComponent
}

fun Fragment.dagger(): ApplicationComponent {
    return (activity!!.application as App).applicationComponent
}

//val Activity.app: App
//    get() = application as App
//
//val Fragment.app: App?
//    get() = activity?.app


//for util library
fun List<Any>.join(separator: String): String {
    return joinToString(separator = separator, transform = { it.toString() })
}