package com.jueggs.stackdownloader.utils

import android.app.Activity
import android.opengl.Visibility
import android.support.v4.app.Fragment
import android.view.View
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.utils.EMPTY_STRING
import com.jueggs.utils.TAG_UNHANDLED_EXCEPTION
import com.jueggs.utils.logTagged
import java.lang.reflect.Field
import java.lang.reflect.Method

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

fun Any.findDeclaredMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.declaredMethods.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findDeclaredField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.declaredFields.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}