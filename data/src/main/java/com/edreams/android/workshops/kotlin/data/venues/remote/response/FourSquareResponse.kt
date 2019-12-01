package com.edreams.android.workshops.kotlin.data.venues.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class FourSquareResponse<out T>(val meta: MetaResponse, val response: T)
