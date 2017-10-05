package com.edreams.android.workshops.kotlin.presentation.places

data class PlaceViewModel(val title: String)

class PlacesPresenter(private val placesListView: PlacesView) {

  fun loadPlaces() {
    placesListView.showPlaces(buildMockPlaces())
  }

  fun onSearch(place: String) {
    placesListView.showPlaces(buildMockPlaces())
  }

  private fun buildMockPlaces() = mutableListOf<PlaceViewModel>().apply {
    add(PlaceViewModel("Barcelona"))
    add(PlaceViewModel("Madrid"))
    add(PlaceViewModel("Santiago de Compostela"))
    add(PlaceViewModel("Sevilla"))
    add(PlaceViewModel("Las Palmas"))
    add(PlaceViewModel("Mallorca"))
    add(PlaceViewModel("Valencia"))
    add(PlaceViewModel("Andalucía"))
    add(PlaceViewModel("Girona"))
    add(PlaceViewModel("Tarragona"))
    add(PlaceViewModel("Vigo"))
  }
}