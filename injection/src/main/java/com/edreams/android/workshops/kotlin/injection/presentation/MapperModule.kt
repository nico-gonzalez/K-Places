package com.edreams.android.workshops.kotlin.injection.presentation

import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.mapper.VenuesUiModelMapper
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {

  @Binds
  abstract fun bindVenueUiModelMapper(
      mapper: VenuesUiModelMapper): Mapper<VenueModel, VenueUiModel>
}