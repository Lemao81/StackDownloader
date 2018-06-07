package com.jueggs.stackdownloader.util

import android.content.Context
import android.databinding.BindingAdapter
import android.os.Build
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.jueggs.andutils.behavior.AbstractVerticalScrollBehavior
import com.jueggs.andutils.util.AppMode
import com.jueggs.customview.stackoverflowtag.StackoverflowTag
import com.jueggs.jutils.cropToRange
import com.jueggs.stackdownloader.*

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

class AdjustPaddingOnScrollBehavior<TView : View>(context: Context, attrs: AttributeSet) : AbstractVerticalScrollBehavior<TView>(context, attrs) {
    private var initialPaddingTop = 0
    private var acceleration: Float = 1f

    init {
        context.withStyledAttributes(attrs, R.styleable.VerticalScrollBehavior) {
            acceleration = getFloat(R.styleable.VerticalScrollBehavior_behavior_acceleration, 1f)
        }
    }

    override fun onLayoutChild(parent: CoordinatorLayout?, child: TView, layoutDirection: Int): Boolean {
        initialPaddingTop = child.paddingTop
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: TView, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        val newTranslationY = child.translationY - dy * acceleration

        child.translationY = cropToRange(-initialPaddingTop.toFloat(), 0f, newTranslationY)
    }
}