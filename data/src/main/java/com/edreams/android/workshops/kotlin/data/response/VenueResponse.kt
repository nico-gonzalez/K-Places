package com.edreams.android.workshops.kotlin.data.response

class VenueResponse(val id: String, val name: String, val rating: Float,
    val photos: Response<PhotoResponse>)