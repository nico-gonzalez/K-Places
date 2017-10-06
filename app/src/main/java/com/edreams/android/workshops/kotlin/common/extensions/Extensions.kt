package com.edreams.android.workshops.kotlin.common.extensions

import android.view.View

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.gone() {
  this.visibility = View.GONE
}