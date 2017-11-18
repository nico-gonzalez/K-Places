package com.edreams.android.workshops.kotlin.injection.data

import android.arch.persistence.room.Room
import android.content.Context
import com.edreams.android.workshops.kotlin.data.common.cache.PlacesDatabase
import com.edreams.android.workshops.kotlin.injection.common.MapperModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(MapperModule::class))
class CacheModule {

  @Singleton
  @Provides
  fun provideDatabase(context: Context): PlacesDatabase = Room.databaseBuilder(context,
      PlacesDatabase::class.java, "places")
      .build()

  @Singleton
  @Provides
  fun provideVenueDao(database: PlacesDatabase) = database.venuesDao()
}