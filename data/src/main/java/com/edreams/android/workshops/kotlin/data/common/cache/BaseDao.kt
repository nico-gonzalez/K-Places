package com.edreams.android.workshops.kotlin.data.common.cache

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

interface BaseDao<T> {

  @Insert(onConflict = REPLACE)
  fun insert(item: T): Long

  @Insert(onConflict = REPLACE)
  fun insert(items: Array<T>): Array<Long>

  @Update(onConflict = REPLACE)
  fun update(item: T): Int

  @Delete
  fun delete(item: T): Int

  @Delete
  fun delete(items: Array<T>): Int
}