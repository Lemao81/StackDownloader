package com.jueggs.stackdownloader

import android.databinding.BindingAdapter
import android.os.Build
import android.widget.TextView
import com.jueggs.andutils.helper.DateRenderer
import java.util.*

fun isLollipopOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isNougatOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

@BindingAdapter(value = ["date", "dateFormat"], requireAll = true)
fun TextView.setDate(date: Date, dateFormat: String) {
    this.text = DateRenderer(dateFormat).render(date.time)
}