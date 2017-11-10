package com.edreams.android.workshops.kotlin.injection.common

import com.edreams.android.workshops.kotlin.data.mapper.VenueMapper
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.mapper.VenuesUiModelMapper
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {

  @Binds
  abstract fun bindVenueUiModelMapper(
      mapper: VenuesUiModelMapper): Mapper<VenueModel, VenueUiModel>

  @Binds
  abstract fun bindVenueModelMapper(
      mapper: VenueMapper): Mapper<VenueResponse, VenueModel>
}