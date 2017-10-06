package com.edreams.android.workshops.kotlin.common.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.edreams.android.workshops.kotlin.presentation.places.VenueViewModel
import kotlinx.android.synthetic.main.venue_item.view.placeTitle

sealed class ViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

  abstract fun bind(data: T)

  class PlaceViewHolder(itemView: View) : ViewHolder<VenueViewModel>(itemView) {
    override fun bind(data: VenueViewModel) = with(data) {
      itemView.placeTitle.text = title
    }
  }
}