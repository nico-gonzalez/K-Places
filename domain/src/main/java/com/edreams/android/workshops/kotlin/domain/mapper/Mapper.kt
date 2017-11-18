package com.edreams.android.workshops.kotlin.domain.mapper

interface Mapper<T, out R> {
  fun map(from: T): R

  fun map(fromList: List<T>) = fromList.map { map(it) }
}