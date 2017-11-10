package com.edreams.android.workshops.kotlin.common.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.edreams.android.workshops.kotlin.common.extensions.loadCircle
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import kotlinx.android.synthetic.main.venue_item.view.placeImage
import kotlinx.android.synthetic.main.venue_item.view.placeTitle
import kotlinx.android.synthetic.main.venue_item.view.ratingBar

sealed class ViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

  abstract fun bind(data: T)

  class PlaceViewHolder(itemView: View) : ViewHolder<VenueUiModel>(itemView) {
    override fun bind(data: VenueUiModel) = with(data) {
      itemView.placeTitle.text = title
      itemView.ratingBar.rating = rating
      itemView.placeImage.loadCircle(photoUrl)
    }
  }
}