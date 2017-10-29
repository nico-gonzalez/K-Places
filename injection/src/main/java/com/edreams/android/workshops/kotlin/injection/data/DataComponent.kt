package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RepositoryModule::class))
interface DataComponent {
  fun venuesRepository(): VenuesRepository
}
