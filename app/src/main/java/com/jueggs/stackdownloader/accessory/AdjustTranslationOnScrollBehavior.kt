package com.jueggs.stackdownloader.accessory

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.jueggs.andutils.behavior.AbstractVerticalScrollBehavior
import com.jueggs.jutils.cropToRange
import com.jueggs.stackdownloader.R
import org.jetbrains.anko.sdk25.coroutines.onAttachStateChangeListener

class AdjustTranslationOnScrollBehavior<TView : View>(context: Context, attrs: AttributeSet) : AbstractVerticalScrollBehavior<TView>(context, attrs) {
    private var targetChild: View? = null
    private var originalTranslationY = 0f
    private var isInitial = true

    init {
        context.withStyledAttributes(attrs, R.styleable.VerticalScrollBehavior) {
            acceleration = getFloat(R.styleable.VerticalScrollBehavior_behavior_acceleration, 1f)
        }
    }

    override fun onLayoutChild(parent: CoordinatorLayout?, child: TView, layoutDirection: Int): Boolean {
        if (targetChild == null && isInitial) {
            targetChild = child.findViewById(R.id.resultRootLayout)
            isInitial = false

            targetChild?.let {
                originalTranslationY = it.translationY
                it.onAttachStateChangeListener {
                    onViewDetachedFromWindow {
                        targetChild = null
                        isInitial = true
                    }
                }
            }
        }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: TView, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        targetChild?.let {
            val newTranslationY = it.translationY - dy * acceleration
            it.translationY = cropToRange(0f, originalTranslationY, newTranslationY)
        }
    }
}