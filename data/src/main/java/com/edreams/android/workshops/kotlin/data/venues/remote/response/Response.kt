package com.edreams.android.workshops.kotlin.data.venues.remote.response

data class Response<out T>(val groups: List<GroupsResponse<T>>)