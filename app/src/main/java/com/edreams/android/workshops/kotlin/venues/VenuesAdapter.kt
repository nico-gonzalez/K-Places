package com.edreams.android.workshops.kotlin.venues

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.edreams.android.workshops.kotlin.R.layout
import com.edreams.android.workshops.kotlin.common.extensions.inflater
import com.edreams.android.workshops.kotlin.common.view.ViewHolder
import com.edreams.android.workshops.kotlin.common.view.ViewHolder.PlaceViewHolder
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel

internal class VenuesAdapter(private val venueItemClickListener: (Int, VenueUiModel) -> Unit)
  : RecyclerView.Adapter<ViewHolder<VenueUiModel>>() {

  private val venues: MutableList<VenueUiModel> = mutableListOf()

  internal fun setPlaces(venues: List<VenueUiModel>) = with(this.venues) {
    val oldVenues = ArrayList(this)
    clear()
    addAll(venues)
    DiffUtil.calculateDiff(VenuesDiffCallback(oldVenues, this))
        .dispatchUpdatesTo(this@VenuesAdapter)
  }

  override fun getItemCount(): Int = venues.size

  override fun onBindViewHolder(holder: ViewHolder<VenueUiModel>?, position: Int) {
    holder?.bind(venues[position])
    holder?.itemView?.setOnClickListener {
      with(holder.adapterPosition) {
        venueItemClickListener(this, venues[this])
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = parent?.let {
    PlaceViewHolder(parent.inflater.inflate(layout.venue_item, parent, false))
  }

  private class VenuesDiffCallback(private val oldVenues: List<VenueUiModel>,
      private val newVenues: List<VenueUiModel>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int,
        newItemPosition: Int): Boolean = newVenues[newItemPosition] == oldVenues[oldItemPosition]

    override fun getOldListSize(): Int = oldVenues.size

    override fun getNewListSize(): Int = newVenues.size

    override fun areContentsTheSame(oldItemPosition: Int,
        newItemPosition: Int): Boolean = newVenues[newItemPosition].title == oldVenues[oldItemPosition].title
  }
}