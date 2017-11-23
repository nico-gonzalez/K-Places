package com.edreams.android.workshops.kotlin.data.venues.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.edreams.android.workshops.kotlin.data.common.cache.BaseDao
import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity

@Dao
abstract class VenuesDao : BaseDao<VenueEntity> {

  @Query("SELECT * FROM venue where query = :query")
  abstract fun findByQuery(query: String): List<VenueEntity>

  @Query("DELETE from venue where query = :query")
  abstract fun truncateByQuery(query: String)

  @Transaction
  open fun clearAndInsert(query: String, items: Array<VenueEntity>): Array<Long> {
    truncateByQuery(query)
    return insert(items)
  }
}