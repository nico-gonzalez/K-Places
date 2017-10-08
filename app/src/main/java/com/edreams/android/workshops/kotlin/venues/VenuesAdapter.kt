package com.edreams.android.workshops.kotlin.venues

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edreams.android.workshops.kotlin.R.layout
import com.edreams.android.workshops.kotlin.common.view.ViewHolder
import com.edreams.android.workshops.kotlin.common.view.ViewHolder.PlaceViewHolder
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel

internal class VenuesAdapter(
    private val venueItemClickListener: VenueItemClickListener) : RecyclerView.Adapter<ViewHolder<VenueUiModel>>() {

  interface VenueItemClickListener {
    fun onVenueClicked(position: Int, venue: VenueUiModel)
  }

  private val venues: MutableList<VenueUiModel> = mutableListOf()

  fun setPlaces(venues: List<VenueUiModel>) = with(this.venues) {
    clear()
    addAll(venues)
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int = venues.size

  override fun onBindViewHolder(holder: ViewHolder<VenueUiModel>?, position: Int) {
    holder?.bind(venues[position])
    holder?.itemView?.setOnClickListener {
      with(holder.adapterPosition) {
        venueItemClickListener.onVenueClicked(this, venues[this])
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
      PlaceViewHolder(
          LayoutInflater.from(parent?.context).inflate(layout.venue_item, parent, false))
}