package com.edreams.android.workshops.kotlin.injection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Provider

@PerApplication
class ViewModelFactory @Inject constructor(
  private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    var creator: Provider<out ViewModel>? = creators[modelClass]
    if (creator == null) {
      for ((key, value) in creators) {
        if (modelClass.isAssignableFrom(key)) {
          creator = value
          break
        }
      }
    }
    if (creator == null) {
      throw IllegalArgumentException("unknown model class $modelClass")
    }
    try {
      return creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }

  }
}
