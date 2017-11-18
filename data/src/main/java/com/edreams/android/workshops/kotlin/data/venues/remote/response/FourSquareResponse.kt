package com.edreams.android.workshops.kotlin.data.venues.remote.response

data class FourSquareResponse<out T>(val response: Response<T>)