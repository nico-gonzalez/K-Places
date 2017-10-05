package com.edreams.android.workshops.kotlin.common.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.edreams.android.workshops.kotlin.presentation.places.PlaceViewModel
import kotlinx.android.synthetic.main.place_item.view.placeTitle

sealed class ViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
  abstract fun bind(data: T)

  class PlaceViewHolder(itemView: View) : ViewHolder<PlaceViewModel>(itemView) {
    override fun bind(data: PlaceViewModel) {
      itemView.placeTitle.text = data.title
    }
  }
}