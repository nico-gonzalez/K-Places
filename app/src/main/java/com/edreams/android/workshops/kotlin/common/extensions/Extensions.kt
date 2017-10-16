package com.edreams.android.workshops.kotlin.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.circleCropTransform

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
  Glide.with(context).load(url)
      .apply(circleCropTransform())
      .into(this)
}

val TextView.textString get() = this.text.toString()

val View.inflater: LayoutInflater get() = LayoutInflater.from(context)