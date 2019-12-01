package com.edreams.android.workshops.kotlin.data.venues.cache.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.edreams.android.workshops.kotlin.data.common.cache.BaseDao
import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity

@Dao
abstract class VenuesDao : BaseDao<VenueEntity> {

  @Query("SELECT * FROM venue where `query` = :query")
  abstract fun findByQuery(query: String): List<VenueEntity>

  @Query("DELETE from venue where `query` = :query")
  abstract fun truncateByQuery(query: String)

  @Transaction
  open fun clearAndInsert(query: String, items: Array<VenueEntity>): Array<Long> {
    truncateByQuery(query)
    return insert(items)
  }

  @Query("SELECT * FROM venue where `id` = :id")
  abstract fun findBy(id: String): VenueEntity
}
