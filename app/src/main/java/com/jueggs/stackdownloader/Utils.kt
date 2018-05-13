package com.jueggs.stackdownloader

import android.databinding.BindingAdapter
import android.os.Build
import android.widget.TextView
import com.jueggs.andutils.helper.DateRenderer
import java.text.SimpleDateFormat
import java.util.*

fun isLollipopOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isNougatOrAbove(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

//TODO lib
@BindingAdapter(value = ["date", "dateFormat", "dateLocale"], requireAll = false)
fun TextView.setDate(date: Date, format: String?, locale:Locale?) {
    text = SimpleDateFormat(format ?: "MM/dd/yy", locale ?: Locale.US).format(date)
}

@BindingAdapter(value = ["renderedDate", "renderedDateFormat"], requireAll = false)
fun TextView.setRenderedDate(date: Date, format: String?) {
    text = DateRenderer(format ?: "MM/dd/yy").render(date.time)
}