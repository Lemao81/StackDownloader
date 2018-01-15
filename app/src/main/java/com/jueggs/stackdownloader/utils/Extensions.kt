package com.jueggs.stackdownloader.utils

import android.app.Activity
import android.support.v4.app.Fragment
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.utils.EMPTY_STRING
import com.jueggs.utils.TAG_UNHANDLED_EXCEPTION
import com.jueggs.utils.logTagged

fun Activity.dagger(): ApplicationComponent {
    return (application as App).applicationComponent
}

fun Fragment.dagger(): ApplicationComponent {
    return (activity!!.application as App).applicationComponent
}

//for util library
fun logUnhandledException(throwable: Throwable) {
    logTagged(TAG_UNHANDLED_EXCEPTION, "${throwable.message}\n", EMPTY_STRING)
    logTagged(TAG_UNHANDLED_EXCEPTION, throwable.stackTrace.joinToString("\n", transform = { it.toString() }), EMPTY_STRING)
}