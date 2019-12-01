package com.edreams.android.workshops.kotlin.common.view.behavior

import android.animation.Animator
import android.content.Context
import androidx.core.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class QuickReturnBehavior(context: Context, attrs: AttributeSet) : Behavior<View>(context, attrs) {

  private val interpolator: FastOutSlowInInterpolator by lazy {
    FastOutSlowInInterpolator()
  }

  private var dySinceDirectionChange = 0
  private var isShowing: Boolean = false
  private var isHiding: Boolean = false

  override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View,
      directTargetChild: View, target: View, axes: Int,
      type: Int): Boolean = ViewCompat.SCROLL_AXIS_VERTICAL != 0

  override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View,
      dx: Int, dy: Int, consumed: IntArray, type: Int) {

    if (dy > 0 && dySinceDirectionChange < 0
        || dy < 0 && dySinceDirectionChange > 0) {
      child.animate().cancel()
      dySinceDirectionChange = 0
    }

    dySinceDirectionChange += dy

    if (dySinceDirectionChange > child.height
        && child.visibility == View.VISIBLE
        && !isHiding) {
      hide(child)
    } else if (dySinceDirectionChange < (0 - child.height)
        && child.visibility == View.INVISIBLE
        && !isShowing) {
      show(child)
    }
  }

  private fun hide(view: View) {
    isHiding = true
    val animator = view.animate()
        .translationY(-view.height.toFloat())
        .setInterpolator(interpolator)
        .setDuration(200)

    animator.setListener(object : Animator.AnimatorListener {
      override fun onAnimationStart(animator: Animator) {}

      override fun onAnimationEnd(animator: Animator) {
        isHiding = false
        view.visibility = View.INVISIBLE
      }

      override fun onAnimationCancel(animator: Animator) {
        isHiding = false
        if (!isShowing) {
          show(view)
        }
      }

      override fun onAnimationRepeat(animator: Animator) {}
    })

    animator.start()
  }

  private fun show(view: View) {
    isShowing = true
    val animator = view.animate()
        .translationY(0f)
        .setInterpolator(interpolator)
        .setDuration(200)

    animator.setListener(object : Animator.AnimatorListener {
      override fun onAnimationStart(animator: Animator) {
        view.visibility = View.VISIBLE
      }

      override fun onAnimationEnd(animator: Animator) {
        isShowing = false
      }

      override fun onAnimationCancel(animator: Animator) {
        isShowing = false
        if (!isHiding) {
          hide(view)
        }
      }

      override fun onAnimationRepeat(animator: Animator) {}
    })

    animator.start()
  }
}