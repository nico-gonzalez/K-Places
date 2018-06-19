package com.edreams.android.workshops.kotlin.data.venues.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class GroupsResponse<out T>(val items: List<T>)