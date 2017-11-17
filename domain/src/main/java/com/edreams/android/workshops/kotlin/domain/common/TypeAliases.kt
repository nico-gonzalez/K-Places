package com.edreams.android.workshops.kotlin.domain.common

import com.edreams.android.workshops.kotlin.domain.interactor.Result
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

typealias Callback<T> = (T) -> Unit
typealias Suspendable<T> = suspend () -> T
typealias GetVenuesResult = Result<List<VenueModel>>