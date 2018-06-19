package com.edreams.android.workshops.kotlin.data.venues.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MetaResponse(val code: Int, val requestId: String)