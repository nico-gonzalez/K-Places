package com.edreams.android.workshops.kotlin.data.venues.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(val prefix: String, val suffix:  String, val width: Int, val height: Int)