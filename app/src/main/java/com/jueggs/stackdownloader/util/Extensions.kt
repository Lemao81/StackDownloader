package com.jueggs.stackdownloader.util

import android.app.Activity
import android.support.v4.app.Fragment
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.dagger.ApplicationComponent

//val Activity.app: App
//    get() = application as App
//
//val Fragment.app: App?
//    get() = activity?.app


//for util library

inline fun <reified T> checkCast(obj: Any) {
    if (!obj::class.java.isAssignableFrom(T::class.java)) throw TypeCastException("class ${obj::class.java.simpleName} must be assignable from ${T::class.java.simpleName}")
}

fun List<Any>.join(separator: String): String {
    return joinToString(separator = separator, transform = { it.toString() })
}