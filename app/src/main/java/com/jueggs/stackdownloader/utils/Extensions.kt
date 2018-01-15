package com.jueggs.stackdownloader.utils

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import com.jueggs.stackdownloader.App
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.utils.extensions.verticalLinearLayoutManager

fun Activity.dagger(): ApplicationComponent {
    return (application as App).applicationComponent
}

fun Fragment.dagger(): ApplicationComponent {
    return (activity!!.application as App).applicationComponent
}

//for util library
fun AdapterView<*>.asString(): String? = if (selectedItem != null) selectedItem.toString() else null

fun List<String>.join(separator: String): String = joinToString(separator) { it }

fun ViewGroup.layoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun RecyclerView.addVerticalLinearLayoutManager(context: Context): RecyclerView {
    layoutManager = context.verticalLinearLayoutManager()
    return this
}

fun RecyclerView.addAdapter(adapter: RecyclerView.Adapter<*>): RecyclerView {
    this.adapter = adapter
    return this
}