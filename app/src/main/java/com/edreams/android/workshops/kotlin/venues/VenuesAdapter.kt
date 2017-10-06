package com.edreams.android.workshops.kotlin.venues

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edreams.android.workshops.kotlin.R.layout
import com.edreams.android.workshops.kotlin.common.view.ViewHolder
import com.edreams.android.workshops.kotlin.common.view.ViewHolder.PlaceViewHolder
import com.edreams.android.workshops.kotlin.presentation.places.VenueViewModel

internal class VenuesAdapter : RecyclerView.Adapter<ViewHolder<VenueViewModel>>() {

  private val venues: MutableList<VenueViewModel> = mutableListOf()

  fun setPlaces(venues: List<VenueViewModel>) = with(this.venues) {
    clear()
    addAll(venues)
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int = venues.size

  override fun onBindViewHolder(holder: ViewHolder<VenueViewModel>?, position: Int) {
    holder?.bind(venues[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
      PlaceViewHolder(
          LayoutInflater.from(parent?.context).inflate(layout.venue_item, parent, false))
}