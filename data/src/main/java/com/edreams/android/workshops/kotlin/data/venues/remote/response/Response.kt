package com.edreams.android.workshops.kotlin.data.venues.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Response<out T>(val groups: List<GroupsResponse<T>>)
