package com.edreams.android.workshops.kotlin.domain.mapper

interface Mapper<in T, out R> {
  fun map(from: T): R

  fun map(fromList: List<T>) = fromList.map { item ->
    map(item)
  }
}