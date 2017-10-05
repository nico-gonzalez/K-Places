package com.edreams.android.workshops.kotlin.places

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edreams.android.workshops.kotlin.R.layout
import com.edreams.android.workshops.kotlin.common.view.ViewHolder
import com.edreams.android.workshops.kotlin.common.view.ViewHolder.PlaceViewHolder
import com.edreams.android.workshops.kotlin.presentation.places.PlaceViewModel

internal class PlacesAdapter : RecyclerView.Adapter<ViewHolder<PlaceViewModel>>() {

  private val places: MutableList<PlaceViewModel> = mutableListOf()

  fun setPlaces(places: List<PlaceViewModel>) = with(this.places) {
    clear()
    addAll(places)
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int = places.size

  override fun onBindViewHolder(holder: ViewHolder<PlaceViewModel>?, position: Int) {
    holder?.bind(places[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
      PlaceViewHolder(
          LayoutInflater.from(parent?.context).inflate(layout.place_item, parent, false))
}