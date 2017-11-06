package com.edreams.android.workshops.kotlin.common.resources

import android.content.Context
import com.edreams.android.workshops.kotlin.R
import com.edreams.android.workshops.kotlin.presentation.resources.ResourceProvider

class AndroidResourceProvider(private val context: Context) : ResourceProvider {

  override fun emptyVenueSearchErrorMessage(): String =
      context.getString(R.string.empty_search_message)

}