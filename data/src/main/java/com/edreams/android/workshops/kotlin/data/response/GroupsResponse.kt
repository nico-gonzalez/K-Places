package com.edreams.android.workshops.kotlin.data.response

data class GroupsResponse<out T>(val items: List<T>)