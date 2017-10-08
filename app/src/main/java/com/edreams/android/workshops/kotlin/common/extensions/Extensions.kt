package com.edreams.android.workshops.kotlin.common.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.gone() {
  this.visibility = View.GONE
}

fun ImageView.load(url: String) {
  Glide.with(context).load(url).into(this)
}

fun ImageView.loadCircle(url: String) {
  Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}