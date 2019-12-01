package com.edreams.android.workshops.kotlin.injection.data

import android.content.Context
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    RepositoryModule::class, CacheModule::class
  ]
)
interface DataComponent {

  fun venuesRepository(): VenuesRepository

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun context(context: Context): Builder

    fun build(): DataComponent
  }
}
