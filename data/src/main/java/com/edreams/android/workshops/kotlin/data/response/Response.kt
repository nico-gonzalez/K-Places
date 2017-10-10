package com.edreams.android.workshops.kotlin.data.response

data class Response<out T>(val groups: List<GroupsResponse<T>>)