package com.edreams.android.workshops.kotlin.data.response

class Response<out T>(val groups: List<GroupsResponse<T>>)