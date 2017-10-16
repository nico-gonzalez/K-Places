package com.edreams.android.workshops.kotlin.data.response

data class FourSquareResponse<out T>(val response: Response<T>)