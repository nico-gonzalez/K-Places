package com.edreams.android.workshops.kotlin.data.venues.remote.response

data class GroupsResponse<out T>(val items: List<T>)