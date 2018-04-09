package com.edreams.android.workshops.kotlin.test.injection.mock.repository

import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesListProducer
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import kotlinx.coroutines.experimental.runBlocking

class VenuesMockRepository : VenuesRepository {
  override suspend fun getVenues(query: String): VenuesListProducer = runBlocking {
    produce {
      send(listOf(
          VenueModel("1",
              "Sagrada familia",
              3.5f,
              "sagradafamilia.png",
              "",
              200,
              listOf("Carretera de la Sagrada", "20", "Barcelona, Spain"),
              20,
              "",
              query),
          VenueModel("2",
              "Arc del Triunfo",
              4.5f,
              "arcdeltriunfo.png",
              "",
              300,
              listOf("Carretera del Triunfo", "3", "Barcelona, Spain"),
              100,
              "",
              query))
      )
    }
  }
}